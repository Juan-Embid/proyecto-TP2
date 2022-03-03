package simulator.launcher;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import simulator.control.Controller;
import simulator.factories.*;
import simulator.model.DequeuingStrategy;
import simulator.model.Event;
import simulator.model.LightSwitchStrategy;
import simulator.model.SetWeatherEvent;
import simulator.model.TrafficSimulator;

public class Main { //comentario

	private final static Integer _timeLimitDefaultValue = 10;
	private static String _inFile = null;
	private static String _outFile = null;
	private static Factory<Event> _eventsFactory = null;
	private static Integer _ticks = _timeLimitDefaultValue;;
	/*List<Builder<LightSwitchingStrategy>> lsbs = new ArrayList<>();
	lsbs.add( new RoundRobinStrategyBuilder() );
	lsbs.add( new MostCrowdedStrategyBuilder() );
	Factory<LightSwitchingStrategy> lssFactory = new BuilderBasedFactory
	<>(lsbs);
	FACTORÍAS!!!!
	List<Builder<DequeuingStrategy>> dqbs = new ArrayList<>();
dqbs.add( new MoveFirstStrategyBuilder() );
dqbs.add( new MoveAllStrategyBuilder() );
Factory<DequeuingStrategy> dqsFactory = new BuilderBasedFactory<>(
dqbs);

	*/

	
	
	private static void parseArgs(String[] args) {

		// define the valid command line options
		//
		Options cmdLineOptions = buildOptions();

		// parse the command line as provided in args
		//
		CommandLineParser parser = new DefaultParser();
		try {
			CommandLine line = parser.parse(cmdLineOptions, args);
			parseHelpOption(line, cmdLineOptions);
			parseInFileOption(line);
			parseOutFileOption(line);
			parseTicksOption(line);
			// if there are some remaining arguments, then something wrong is
			// provided in the command line!
			//
			String[] remaining = line.getArgs();
			if (remaining.length > 0) {
				String error = "Illegal arguments:";
				for (String o : remaining)
					error += (" " + o);
				throw new ParseException(error);
			}

		} catch (ParseException e) {
			System.err.println(e.getLocalizedMessage());
			System.exit(1);
		}

	}

	private static Options buildOptions() {
		Options cmdLineOptions = new Options();

		cmdLineOptions.addOption(Option.builder("i").longOpt("input").hasArg().desc("Events input file").build());
		cmdLineOptions.addOption(
				Option.builder("o").longOpt("output").hasArg().desc("Output file, where reports are written.").build());
		cmdLineOptions.addOption(Option.builder("h").longOpt("help").desc("Print this message").build());
		cmdLineOptions.addOption(Option.builder("t").longOpt("ticks").hasArg().desc("Ticks to the simulator’s main loop (default\r\n" + 
				"value is 10).").build());
		return cmdLineOptions;
	}

	private static void parseTicksOption(CommandLine line) {
		if(line.hasOption("t")) 
			_ticks =Integer.parseInt(line.getOptionValue("t"));
	}
	
	private static void parseHelpOption(CommandLine line, Options cmdLineOptions) {
		if (line.hasOption("h")) {
			HelpFormatter formatter = new HelpFormatter();
			formatter.printHelp(Main.class.getCanonicalName(), cmdLineOptions, true);
			System.exit(0);
		}
	}

	private static void parseInFileOption(CommandLine line) throws ParseException {
		_inFile = line.getOptionValue("i");
		if (_inFile == null) {
			throw new ParseException("An events file is missing");
		}
	}

	private static void parseOutFileOption(CommandLine line) throws ParseException {
		_outFile = line.getOptionValue("o");
	}

	private static void initFactories() {
		List<Builder<LightSwitchStrategy>> lsbs = new ArrayList<>();
		lsbs.add( new RoundRobinStrategyBuilder() );
		lsbs.add( new MostCrowdedStrategyBuilder() );
		Factory<LightSwitchStrategy> lssFactory = new BuilderBasedFactory<>(lsbs);
		
		List<Builder<DequeuingStrategy>> dqbs = new ArrayList<>();
		dqbs.add( new MoveFirstStrategyBuilder() );
		dqbs.add( new MoveAllStrategyBuilder() );
		Factory<DequeuingStrategy> dqsFactory = new BuilderBasedFactory<>(dqbs);
		
		ArrayList<Builder<Event>> eventsBu = new ArrayList<>();
		eventsBu.add(new NewCityRoadEventBuilder());
		eventsBu.add(new NewInterCityRoadEventBuilder());
		eventsBu.add(new NewVehicleEventBuilder());
		eventsBu.add(new NewJunctionEventBuilder(lssFactory, dqsFactory));
		eventsBu.add(new SetWeatherEventBuilder());
		eventsBu.add(new SetContClassEventBuilder());
		_eventsFactory = new BuilderBasedFactory<Event>(eventsBu);
	}

	private static void startBatchMode() throws IOException {
		TrafficSimulator simulator = new TrafficSimulator();
		Controller control = new Controller(simulator, _eventsFactory);
		OutputStream o;
		try(InputStream in = new FileInputStream(new File(_inFile));){
			control.loadEvents(in);
			if(_outFile == null)
				o = System.out;
			else
				o = new FileOutputStream(_outFile);
			control.run(_ticks, o);
			
			in.close();
			o.close();
		}catch(FileNotFoundException e) {
			throw new IOException("Input file not found");
		}
	}

	private static void start(String[] args) throws IOException {
		initFactories();
		parseArgs(args);
		startBatchMode();
	}

	// example command lines:
	//
	// -i resources/examples/ex1.json
	// -i resources/examples/ex1.json -t 300
	// -i resources/examples/ex1.json -o resources/tmp/ex1.out.json
	// --help

	public static void main(String[] args) {
		try {
			start(args);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
