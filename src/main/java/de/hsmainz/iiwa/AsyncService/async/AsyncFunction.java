package de.hsmainz.iiwa.AsyncService.async;

import de.hsmainz.iiwa.AsyncService.executor.layer.ExecutorLayer;
import de.hsmainz.iiwa.AsyncService.future.ListenableFuture;
import de.hsmainz.iiwa.AsyncService.functional.Function;

public class AsyncFunction <T, R> implements AsyncTask {

    private ExecutorLayer exec;
    private T input;
    private Function<T, R> func;
    private ListenableFuture<R> future;

    public AsyncFunction(Function<T, R> function){
        func = function;
    }

    public AsyncFunction(ExecutorLayer ctx, Function<T, R> function){
        func = function;
        exec = ctx;
    }

    public AsyncFunction(T in, Function<T, R> function){
        input = in;
        func = function;
    }

    public AsyncFunction(T in, Function<T, R> function, ExecutorLayer ctx){
        input = in;
        func = function;
        exec = ctx;
    }

    @Override
    public void execute() {
        R result = func.apply(input);
        if(future != null){
            future.fire(result);
        }
    }

    @Override
    public void bindLayer(ExecutorLayer ctx) {
        exec = ctx;
    }

    @Override
    public ExecutorLayer layer(){
        return exec;
    }

    @Override
    public void fire() {
        if(exec != null){
            exec.post(this);
        } else {
            execute();
        }
    }

    public void fire(T arg) {

        __set__arg_(arg);

        if(exec != null){
            exec.post(this);
        } else {
            execute();
        }
    }

    @Override
    public AsyncTask copy() throws CloneNotSupportedException {
        return null;
    }

    @Override
    public <K> void __set__arg_(K arg) {
        input = (T) arg;
    }

    @Override
    public <L> void __set__sec__arg_(L arg) { }

    @Override
    public ListenableFuture<R> future() {
        if(future == null){
            future = new ListenableFuture<R>(this);
        }
        return future;
    }
}
