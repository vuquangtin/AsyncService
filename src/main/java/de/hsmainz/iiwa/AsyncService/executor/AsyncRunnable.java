package de.hsmainz.iiwa.AsyncService.executor;


public class AsyncRunnable implements AsyncTask {

    private ExecutionContext exec;
    private Runnable func;
    private ListenableFuture<Object> future;

    public AsyncRunnable(Runnable function){
        func = function;
    }

    public AsyncRunnable(ExecutionContext ctx, Runnable runnable){
        func = runnable;
        exec = ctx;
    }

    @Override
    public void execute() {
        func.run();
        if(future != null){
            future.fire();
        }
    }

    @Override
    public void bindContext(ExecutionContext ctx) {
        exec = ctx;
    }

    @Override
    public ExecutionContext context(){
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

    @Override
    public AsyncTask copy() throws CloneNotSupportedException {
        return null;
    }

    @Override
    public <K> void __set__arg_(K arg) { }

    @Override
    public <L> void __set__sec__arg_(L arg) { }

    @Override
    public ListenableFuture<Object> future() {
        if(future == null){
            future = new ListenableFuture<Object>(this);
        }
        return future;
    }
}