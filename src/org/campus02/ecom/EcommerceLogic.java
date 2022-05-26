package org.campus02.ecom;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class EcommerceLogic implements Runnable{

    private Socket socket;

    public EcommerceLogic(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {

        try(BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()))) {

            String command;
            BasketAnalyzer ba = null;
            while((command = br.readLine()) != null){

                System.out.println("Received command: " + command);
                String[] cmd = command.split(" ");

                if(cmd.length == 2){
                    if(cmd[0].equalsIgnoreCase("openfile")){
                        ArrayList<BasketData> list = null;
                        try {
                            list = BasketDataLoader.load(cmd[1]);
                        } catch (DataFileException e) {
                            e.printStackTrace();
                        }
                        ba = new BasketAnalyzer(list);
                        bw.write("<<< basket data loaded with " + list.size() + " entries >>>");
                        bw.newLine();
                        bw.flush();
                    }
                    else if(cmd[0].equalsIgnoreCase("geteverynth") && ba != null){
                        List<BasketData> list = ba.getEveryNthBasket(Integer.parseInt(cmd[1]));
                        for (BasketData basketData : list) {
                            bw.write(basketData.toString());
                            bw.newLine();
                        }
                        bw.flush();
                    }
                    else{
                        bw.write("unknown command");
                        bw.newLine();
                        bw.flush();
                    }
                }
                else if(cmd.length == 1){
                    if(cmd[0].equalsIgnoreCase("getstats") && ba != null){
                        HashMap<String, ArrayList<Double>> map = ba.groupByProductCategory();
                        for (String key : map.keySet()) {
                            ArrayList<Double> list = map.get(key);
                            double sum = 0;
                            for (Double d : list) {
                                sum += d;
                            }
                            sum = sum/list.size();
                            bw.write(key + " - " + sum);
                            bw.newLine();
                        }
                        bw.flush();
                    }
                    else if(cmd[0].equalsIgnoreCase("exit")){
                        bw.write("bye!");
                        bw.newLine();
                        bw.flush();
                        bw.close();
                        br.close();
                        socket.close();
                        return;
                    }
                    else{
                        bw.write("unknown command");
                        bw.newLine();
                        bw.flush();
                    }
                }
                else{
                    bw.write("unknown command");
                    bw.newLine();
                    bw.flush();
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
