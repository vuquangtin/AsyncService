package de.hsmainz.iiwa.AsyncService.async;

import de.hsmainz.iiwa.AsyncService.executor.layer.ExecutorLayer;
import de.hsmainz.iiwa.AsyncService.future.ListenableFuture;

public interface AsyncTask {
	
	/**
	 * Executes the event immediately. This should only be called by the AsyncService.
	 */
	public void execute();

	/**
	 *
	 * @param layer
	 */
	public void bindLayer(ExecutorLayer layer);

	/**
	 *
	 */
	public ExecutorLayer layer();

	/**
	 *
	 */
	public void fire();
	
	/**
	 * Returns a separate copy of this AsyncTask.
	 * @return the new AsyncTask
	 */
	public AsyncTask copy() throws CloneNotSupportedException;

	/**
	 * DO NOT USE THIS METHOD. FOR INTERNAL USE ONLY
	 * @param <K> Argument Type
	 * @param arg Argument to set
	 */
	public <K> void __set__arg_(K arg);
	
	/**
	 * DO NOT USE THIS METHOD. FOR INTERNAL USE ONLY
	 * @param <L> Argument Type
	 * @param arg Argument to set
	 */
	public <L> void __set__sec__arg_(L arg);
	
	/**
	 * Returns the generic ListenableFuture 
	 * @return Returns the ListenableFuture Object
	 * @param <T> the Type of Resource that will be available in the Future
	 * @see ListenableFuture
	 */
	public <T> ListenableFuture<T> future();
	
	public int hashCode();
	
}
