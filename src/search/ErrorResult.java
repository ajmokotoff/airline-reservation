/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package search;

/**
 *
 * 
 */
public class ErrorResult extends Result {
    
    private final String errorStr;
    
    public ErrorResult(String error)
    {
        errorStr = error;
    }
    
    public String getError()
    {
        return errorStr;
    }

}
