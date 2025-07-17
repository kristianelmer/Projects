package inf112.core.audio;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;

public class AudioController {
  private final Sound powerupSound;
  private final GameSong mainMusic;
  private final GameSong battleMusic;
  private boolean isMainMusicPlaying;
  private boolean isBattleMusicRunning;
  private boolean isMuted;

  /* Audio controller with official game audio */
  public AudioController() {
    this.powerupSound = Gdx.audio.newSound(Gdx.files.internal("sounds/powerup.ogg"));
    this.mainMusic = new GameSong("music/LuigisMansionGhostTheme.mid");
    this.battleMusic = new GameSong("music/LuigisMansionMainTheme.mid");
    this.mainMusic.run();
    this.isMainMusicPlaying = true;
    this.isBattleMusicRunning = false;
    this.isMuted = false;
  }

  /* Create audiocontroller with input audio */
  public AudioController(Sound powerupSound, GameSong mainMusic, GameSong battleMusic) {
    this.powerupSound = powerupSound;
    this.mainMusic = mainMusic;
    this.battleMusic = battleMusic;
    this.mainMusic.run();
    this.isMainMusicPlaying = true;
    this.isBattleMusicRunning = false;
    this.isMuted = false;
  }

  /**
   * Pauses or unpauses music playing
   */
  public void toggleMuteGame() {
    if (isMuted) {
      if (isMainMusicPlaying)
        mainMusic.doUnpauseMidiSounds();
      else {
        if (isBattleMusicRunning)
          battleMusic.doUnpauseMidiSounds();
        else {
          battleMusic.run();
          isBattleMusicRunning = true;
        }
      }
      isMuted = false;
    } else {
      if (isMainMusicPlaying)
        mainMusic.doPauseMidiSounds();
      else
        battleMusic.doPauseMidiSounds();
      isMuted = true;
    }
  }

  /**
   * Plays powerup sound as long as game is not muted.
   */
  public void playPowerupSound() {
    if (!isMuted)
      powerupSound.play();
  }

  /**
   * Pauses main music. Switches isMainMusicPlaying to false. If the game is not
   * muted, battleMusic starts running. If muted the isBattleMusicRunning value
   * remains unchanged until toggleMuteGame() is called.
   */
  public void switchToBattleMusic() {
    if (isMainMusicPlaying) {
      mainMusic.doPauseMidiSounds();
    }
    isMainMusicPlaying = false;

    if (!isMuted) {
      battleMusic.run();
      isBattleMusicRunning = true;
    }
  }

  /**
   * Stops battlemusic from running. Switches isMainMusicPlaying to true. If the
   * game is not muted, mainMusic is unpaused.
   */
  public void switchToMainMusic() {
    battleMusic.doStopMidiSounds();
    isBattleMusicRunning = false;

    if (!isMuted) {
      mainMusic.doUnpauseMidiSounds();
    }

    isMainMusicPlaying = true;
  }

  /**
   * Key input for music. M to toggleMuteGame().
   */
  public void handleMusicInput() {
    if (Gdx.input.isKeyJustPressed(Input.Keys.M)) {
      toggleMuteGame();
    }
  }

  /**
   * Checks if the game audio is currently muted.
   * 
   * @return true if muted, false otherwise
   */
  protected boolean isMuted() {
    return isMuted;
  }

  /**
   * Checks if main music is currently playing.
   * 
   * @return true if main music is playing
   */
  protected boolean isMainMusicPlaying() {
    return isMainMusicPlaying;
  }

  /**
   * Checks if battle music is currently running.
   * 
   * @return true if battle music is playing
   */
  protected boolean isBattleMusicRunning() {
    return isBattleMusicRunning;
  }

  /**
   * Disposes of all audio resources when the game is closed.
   */
  public void dispose() {
    powerupSound.dispose();
    mainMusic.doStopMidiSounds();
    battleMusic.doStopMidiSounds();
  }
}
