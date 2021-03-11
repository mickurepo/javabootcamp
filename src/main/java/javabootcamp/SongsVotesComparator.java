package javabootcamp;

import java.util.Comparator;

public class SongsVotesComparator implements Comparator<Song>{

	@Override
	public int compare(Song o1, Song o2) {
		return -1 * Integer.compare(o1.getVotes(), o2.getVotes());
	}

}
