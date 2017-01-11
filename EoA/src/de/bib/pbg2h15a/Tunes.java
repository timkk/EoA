package de.bib.pbg2h15a;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;


public enum Tunes {
		/**
		 * Represents the music-files.
		 * Can be used to play music in the menues/game.
		 */
	
		//Music
		MUSIC_GAME_BACKGROUND("assets/sounds/music/game_background_music.mp3"),	
		MUSIC_GAME_FINISHED("assets/sounds/music/game_finished_music.mp3"),
		MUSIC_MENU("assets/sounds/music/menu_music.mp3");
		private Music music;
		
		
		private Tunes(String path) {
			this.music = Gdx.audio.newMusic(Gdx.files.internal(path));
		}


		public Music getSound() {
			return this.music;
		}

		public void Play()
		{
			this.music.play();
		}
		
		

}
