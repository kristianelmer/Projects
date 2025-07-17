package inf112.core.audio;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.badlogic.gdx.audio.Sound;

class AudioControllerTest {

  private GameSong mockMainMusic;
  private GameSong mockBattleMusic;
  private Sound mockSound;
  private AudioController controller;

  @BeforeEach
  void setup() {
    mockMainMusic = mock(GameSong.class);
    mockBattleMusic = mock(GameSong.class);
    mockSound = mock(Sound.class);
    controller = new AudioController(mockSound, mockMainMusic, mockBattleMusic);
  }

  @Test
  void toggleMute_flipsIsMuted() {
    assertFalse(controller.isMuted());
    controller.toggleMuteGame();
    assertTrue(controller.isMuted());
    controller.toggleMuteGame();
    assertFalse(controller.isMuted());
  }

  @Test
  void switchToBattleMusic_updatesFlagsCorrectlyWhenNotMuted() {
    controller.switchToBattleMusic();
    assertFalse(controller.isMainMusicPlaying());
    assertTrue(controller.isBattleMusicRunning());
  }

  @Test
  void switchToBattleMusic_doesNotRunBattleMusicIfMuted() {
    controller.toggleMuteGame(); // mute
    controller.switchToBattleMusic();
    assertFalse(controller.isMainMusicPlaying());
    assertFalse(controller.isBattleMusicRunning());
  }

  @Test
  void switchToMainMusic_updatesFlagsCorrectlyWhenNotMuted() {
    controller.switchToBattleMusic();
    controller.switchToMainMusic();
    assertTrue(controller.isMainMusicPlaying());
    assertFalse(controller.isBattleMusicRunning());
  }

  @Test
  void switchToMainMusic_worksCorrectlyWhileMuted() {
    controller.switchToBattleMusic();
    controller.toggleMuteGame(); // mute
    controller.switchToMainMusic();
    assertTrue(controller.isMainMusicPlaying());
    assertFalse(controller.isBattleMusicRunning());
  }

  @Test
  void toggleMute_unmutesAndRunsBattleMusicIfNotStarted() {
    controller.switchToBattleMusic();
    controller.toggleMuteGame(); // mute
    controller.toggleMuteGame(); // unmute
    assertTrue(controller.isBattleMusicRunning());
  }
}
