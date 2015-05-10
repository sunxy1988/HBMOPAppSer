package com.hbmop.app.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public abstract class AbstractModel implements Serializable,Cloneable{
	public AbstractModel clone(){
		AbstractModel o = null;  
        try {  
            o = (AbstractModel) super.clone();  
        } catch (CloneNotSupportedException ex) {  
            ex.printStackTrace();  
        }  
  
        return o;
	}
}