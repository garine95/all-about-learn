package garine.learn.activity.service.services.processor;



public interface RewardProcessor<T> {

     /**
      * 领奖
      * @param activityDrawContext
      */
     T doReword(T activityDrawContext);
}
