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
	
	@Override
	public World remove(int index){
		World r =  super.remove(index);
		World rPrev = r.getPrev();
		World rNext = r.getNext();
		if(rPrev !=null){
			rPrev.setNext(rNext);
		}
		if(rNext !=null){
			rNext.setPrev(rPrev);
		}
		return r;
	}
}
