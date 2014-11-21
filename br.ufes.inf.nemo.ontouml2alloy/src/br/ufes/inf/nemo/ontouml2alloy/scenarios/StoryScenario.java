package br.ufes.inf.nemo.ontouml2alloy.scenarios;

public class StoryScenario extends Scenario{
	enum Type {LINEAR, FUTURES, COUNTER}
	
	int numberOfWorlds;
	int storyDepth;
	Type storyType;
	
	public StoryScenario (int numberOfWorlds, int storyDepth){
		setNumberOfWorlds(numberOfWorlds);
		setStoryDepth(storyDepth);
		setMode(MODE.PRED);
	}
	
	public int getNumberOfWorlds() {
		return numberOfWorlds;
	}
	
	public void setNumberOfWorlds(int numberOfWorlds) {
		this.numberOfWorlds = checkNumberOfWorlds(numberOfWorlds);
	}
	
	public int getStoryDepth() {
		return storyDepth;
	}
	public void setStoryDepth(int storyDepth) {
		this.storyDepth = checkStoryDepth(storyDepth);
	}
	
	
	public int checkNumberOfWorlds(int value){
		switch (storyType) {
			case COUNTER:
				if (value>4)
					return value;
				return 4;
			case FUTURES:
				if (value>3)
					return value;
				return 3;
			case LINEAR:
				if (value>0)
					return value;
				return 1;
		}
		
		return 1;
	}
	
	public int checkStoryDepth(int value) {
		switch (storyType) {
			case COUNTER:
				if (value>2)
					return value;
				return 2;
			case FUTURES:
				return 1;
			case LINEAR:
				if (value>1)
					return value;
				return 1;
		}
		
		return 1;
	}

	@Override
	public String getString() {
		switch (storyType) {
			case COUNTER:
				return "things may have taken a different outcome in the past";
			case FUTURES:
				return "different outcomes for a given situation";
			case LINEAR:
				return "linear story";
		}
		
		return "";
	}

	@Override
	public String getAlloy() {
		switch (storyType) {
			case COUNTER:
				return "some w1,w2:World | w1!=w2 && next.w1=next.w2 && (some w1.next or some w2.next)";
			case FUTURES:
				return "one w:World | no next.w && all w2:World | w!=w2 implies w2 in w.next";
			case LINEAR:
				return "some w1,w2:World | no w1.next and no next.w2";
		}
		
		return "";
	}

	@Override
	public String getScenarioName() {
		return "Story";
	}
}
