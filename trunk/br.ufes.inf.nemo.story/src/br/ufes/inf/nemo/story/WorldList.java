package br.ufes.inf.nemo.story;

import java.util.ArrayList;

import stories.World;

public class WorldList extends ArrayList<World> {
	public boolean add(World w){
		if(! this.isEmpty()){
			World wprev = this.get(this.size()-1);
			wprev.setNext(w);
			w.setPrev(wprev);
		}		
		return super.add(w);
	}
}
