package google;

import java.util.*;


public class WorkerJobs {
  public static void main(String[] args) {
    WorkerJobs ins = new WorkerJobs();
    int[] jobs = new int[] {1,3,1};
    System.out.println(ins.shortestTime(jobs));
  }
  // start from 1 worker and worker can train others using 1 time unit
  //get the shortest time to finish all jobs
  /*
   * eg: {1,3,1,5}
   * 1: w = 2
   * 2: 1 2 1 4 w = 2;
   * 3: 1 2 1 3 w = 3;
   * 4: 1 1 1 2 w = 4
   * 5: return 2 4+2 = 6
   * 
   * 
   * {1,3,1}
   * 1: w = 2
   * 2: 1 2 1 w = 3
   * 3: return 2
   */
  public int shortestTime( int[] jobs) {
    PriorityQueue<Integer> pq = new PriorityQueue<Integer>(Collections.reverseOrder());
    for( int job: jobs)
      pq.add(job);
    return dfs(pq, 1);
  }
  public int dfs(PriorityQueue<Integer> jobs, int w) {
    if(jobs.isEmpty()) return 0;
    if( jobs.size() <= w) return jobs.peek(); //finish the longest job
    
    int min = Integer.MAX_VALUE;
    
    for( int i = 0; i<= w; i++) {
      // do the jobs
      //新建一个副本，管你怎么搞
      PriorityQueue<Integer> jobs2 = new PriorityQueue<>(jobs);
      updateJobs(jobs2, w - i);
      int newWorker = w + i; //next round w + i(trained)
      min = Math.min(min, dfs(jobs2, newWorker ));
    }
    return 1+min;
  }
    void updateJobs(PriorityQueue<Integer> jobs, int w) {
    //assign w to do w longest jobs
    List<Integer> uJobs = new ArrayList<>();
    for(int i = 0; i<w; i++) {
      int val = jobs.poll() - 1;
      if(val!= 0) uJobs.add(val);
    }
    jobs.addAll(uJobs);
  }
}
