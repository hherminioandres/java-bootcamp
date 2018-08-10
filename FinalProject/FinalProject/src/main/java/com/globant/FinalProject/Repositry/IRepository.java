package com.globant.FinalProject.Repositry;

/**
 * This interface defines the basic behavior of a repository
 * @author Herminio Andres Hernandez
 * @version 0708/2018
 * @param <T>
 */
public interface IRepository <T>{

	public T add(T element);
	public T find(String element);
	public T update(T element);
	public void delete(String elements);
}
