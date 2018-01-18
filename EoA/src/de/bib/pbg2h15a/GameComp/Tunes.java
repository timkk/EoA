package de.bib.pbg2h15a.GameComp;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

import de.bib.pbg2h15a.GameState.GameStateManager;

public enum Tunes {

	/**
	 * Stellt die Musik als <code>Enum</code> zu Verf�gung um diese abzuspielen.
	 * (Kommentiert von pbg2h15akl)
	 */

	
	MUSIC_GAME_BACKGROUND("sounds/music/game_background_music.mp3"),	
	MUSIC_GAME_FINISHED("sounds/music/game_finished_music.mp3"),
	MUSIC_MENU("sounds/music/menu_music.mp3");
	private Music music;

	private Tunes(String path) {
		this.music = Gdx.audio.newMusic(Gdx.files.internal(path));
		music.setLooping(true);
	}


	public Music getSound() {
		return this.music;
	}

	
	/**
	 * Spielt die Musik ab. Alle anderen Musik-Objekte die bisher abgespielt werden, werden gestoppt.
	 * Die Musik wiederholt sich automatisch wenn Sie einmal durchgelaufen ist.
	 * (Kommentiert von pbg2h15akl)
	 */
	public void Play(){
		for (Tunes m : Tunes.values()) {
			m.getSound().stop();
		}
		if(GameStateManager.MUSIC)
			this.music.play();
	}

}