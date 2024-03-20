import java.io.*;
import java.util.*;

public class Sim {

    public static void run_sim(MBTA mbta, Log log) {

        mbta.init_sim();

        mbta.checkStart();

        List<Train> Ts = new ArrayList<>();
        List<Passenger> Ps = new ArrayList<>();

//        List<Thread> thrs = new ArrayList<>();

        for (Map.Entry<Train, List<Station>> entry : mbta.getLines().entrySet()) {
            Ts.add(entry.getKey());
        }

        for (Map.Entry<Passenger, List<Station>> entry : mbta.getTrips().entrySet()) {
            Ps.add(entry.getKey());
        }


        Thread t1 = new Thread(new TrainThread(mbta, Ts.get(0), log));
        Thread t2 = new Thread(new TrainThread(mbta, Ts.get(1), log));
        Thread t3 = new Thread(new TrainThread(mbta, Ts.get(2), log));
        Thread t4 = new Thread(new TrainThread(mbta, Ts.get(3), log));

        Thread t5 = new Thread(new PassThread(mbta, Ps.get(0), log));
        Thread t6 = new Thread(new PassThread(mbta, Ps.get(1), log));
        Thread t7 = new Thread(new PassThread(mbta, Ps.get(2), log));

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
        t6.start();
        t7.start();

//        try {


//            t1.join();
//            t2.join();
//            t3.join();
//            t4.join();
//            t5.join();
//            t6.join();
//            t7.join();

//        } catch (InterruptedException e) {
//            System.out.println(e);
//        }
    }

    public static void main(String[] args) throws Exception {
        if (args.length != 1) {
            System.out.println("usage: ./sim <config file>");
            System.exit(1);
        }

        MBTA mbta = new MBTA();
        mbta.loadConfig(args[0]);

        Log log = new Log();

        run_sim(mbta, log);

        String s = new LogJson(log).toJson();
        PrintWriter out = new PrintWriter("/Users/chukwuemekaoguike/IdeaProjects/MBTASimulation/src/log.json");
        out.print(s);
        out.close();

        mbta.reset();
        mbta.loadConfig(args[0]);
        Verify.verify(mbta, log);


    }
}
