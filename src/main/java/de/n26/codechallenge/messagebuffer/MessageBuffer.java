package de.n26.codechallenge.messagebuffer;

import de.n26.codechallenge.model.Statistic;
import de.n26.codechallenge.model.Transaction;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class MessageBuffer  {

    private final int MAX_SIZE = 60000;
    private boolean updateBuffer = true;
    private final Statistic stat = new Statistic();

    private  Map buffer = Collections.synchronizedMap(new LinkedHashMap<Long, List<Transaction>>(){
        @Override
        protected boolean removeEldestEntry(Map.Entry<Long, List<Transaction>> eldest) {
            return size() > MAX_SIZE;
        }
    });


    public synchronized Map getBuffer(){
        if(buffer.isEmpty()){
            initBuffer(buffer, System.currentTimeMillis());
        }
        return buffer;
    }

    private void initBuffer(Map buffer, Long creationTime) {
        Long start = creationTime - 60000;
        for (Long i= start; i <= creationTime; i++){
            buffer.put(i, new ArrayList<Transaction>());
        }
        new Thread(new UpdateBufferThread()).start();
        new Thread(new StatsComputerThread()).start();
    }

    private class UpdateBufferThread implements Runnable{

        @Override
        public void run() {
            while (updateBuffer){
                buffer.put(System.currentTimeMillis(), new ArrayList<Transaction>());
            }
        }
    }

    private class StatsComputerThread implements Runnable{

        @Override
        public void run() {
            while (updateBuffer){
                stat.resetTransactionsCount();
                buffer.forEach((k,v)->{
                    if(v instanceof List){
                        List<Transaction> transactions = (List<Transaction>) v;
                        if(!transactions.isEmpty()){
                            for(Transaction transaction : transactions ){
                                stat.incrementTransactionsCount();
                            }
                        }
                    }
                });
            }
        }
    }

}

