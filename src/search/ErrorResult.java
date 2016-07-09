/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package search;

/**
 * ErrorResult class
 * <p>
 *     Only Result extension that isn't SearchResult, used to return error information.
 * </p>
 *
 * @author Mike
 */
public class ErrorResult extends Result {
    
    private final String errorStr;
    
    public ErrorResult(String error)
    {
        errorStr = error;
    }
    
    
    // Return error string
    public String getError()
    {
        return errorStr;
    }

}
