package de.bib.pbg2h15a;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

public enum Accoustic {
	
	//Music
	MUSIC_GAME_BACKGROUND("sounds/music/game_background_music.mp3"),	
	MUSIC_GAME_FINISHED("sounds/music/game_finished_music.mp3"),
	MUSIC_MENU("sounds/music/menu_music.mp3"),
	
	
	//Effects
	EFFECT_EXPLOSION("sounds/effects/explosion_effect.mp3"),
	EFFECT_BOMB_DROPPED("sounds/effects/bomb_dropped_effect.mp3"),
	EFFECT_POWERUP_COLLECTED("sounds/effects/powerup_collected_effect.mp3"),
	EFFECT_ILLNESS_COLLECTED("sounds/effects/illness_collected_effect.mp3"),
	EFFECT_DEBUFF_COLLECTED("sounds/effects/debuff_collected.mp3"),
	EFFECT_ENTER_TRAP("sounds/effects/entered_trap_effect.mp3"),
	EFFECT_BUTTON_CLICKED("sounds/effects/menu_click_1_effect.mp3"),
	EFFECT_PLAYER_DIES("sounds/effects/player_dies_effect.mp3"),
	EFFECT_WALL_DESTROYED("sounds/effects/wall_destroyed_effect.mp3");
	
	private Sound sound;
	
	
	private Accoustic(String path) {
		this.sound = Gdx.audio.newSound(Gdx.files.internal(path));
	}


	public Sound getSound() {
		return sound;
	}

	
	
}