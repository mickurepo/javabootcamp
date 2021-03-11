package javabootcamp;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "songs")
@XmlAccessorType (XmlAccessType.FIELD)
public class SongsHelper {
	@XmlElement(name = "song")
	private List<SongHelper> songHelpers = null;

	public List<SongHelper> getSongHelpers() {
		return songHelpers;
	}

	public void setSongHelpers(List<SongHelper> songHelpers) {
		this.songHelpers = songHelpers;
	}

}
