package de.hsmainz.iiwa.AsyncService.executor;

public class Event<T> extends ListenableBase<T> {

    private ExecutionContext ctx;

    public Event(ExecutionContext context){
        ctx = context;
    }

    public void defer(T value){
        for(AsyncTask element : get_queue()){
            element.__set__arg_(value);
            ctx.defer(element);
        }
    }

    public void post(T value){
        for(AsyncTask element : get_queue()){
            element.__set__arg_(value);
            ctx.post(element);
        }
    }

    public void dispatch(T value){
        for(AsyncTask element : get_queue()){
            element.__set__arg_(value);
            ctx.dispatch(element);
        }
    }

    @Override
    protected <R> Listenable<R> get_next_listenable(AsyncTask tsk) {
        return tsk.future();
    }
}