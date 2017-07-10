package net.johnbrooks.fjg.audio;

import org.lwjgl.LWJGLException;
import org.lwjgl.openal.AL;
import org.lwjgl.openal.AL10;
import org.newdawn.slick.openal.WaveData;
import java.util.HashMap;
import java.util.Random;

/**
 * Created by ieatl on 7/3/2017.
 */
public class AudioManager
{
    private static AudioManager instance;
    public static AudioManager getInstance()
    {
        if (instance == null)
            instance = new AudioManager();
        return instance;
    }

    private HashMap<Music, Integer> musicMap;
    private HashMap<Sound, Integer> soundMap;
    private int musicSourceId;
    private int soundSourceId;
    private boolean playing;
    private boolean mutedSound;

    public boolean isPlaying()
    {
        return playing;
    }
    public boolean isMutedSound() { return mutedSound; }
    public void setMutedSound(boolean mute)
    {
        mutedSound = mute;
    }

    private AudioManager()
    {
        try
        {
            AL.create();
        } catch (LWJGLException e)
        {
            e.printStackTrace();
        }
        musicMap = new HashMap<>();
        soundMap = new HashMap<>();
        init();
        loadBuffers();
    }

    private void init()
    {
        musicSourceId = AL10.alGenSources();
        soundSourceId = AL10.alGenSources();
        AL10.alSourcef(musicSourceId, AL10.AL_GAIN, 1);
        AL10.alSourcef(musicSourceId, AL10.AL_PITCH, 1);
        AL10.alSource3f(musicSourceId, AL10.AL_POSITION, 0, 0, 0);
        AL10.alListener3f(AL10.AL_POSITION, 0, 0, 0);
        AL10.alListener3f(AL10.AL_VELOCITY, 0, 0, 0);

    }

    private void loadBuffers()
    {
        for (Music m : Music.values())
            musicMap.put(m, loadSound(m.path));
        for (Sound s : Sound.values())
            soundMap.put(s, loadSound(s.path));
    }

    public int loadSound(String file)
    {
        int buffer = AL10.alGenBuffers();
        WaveData waveData = WaveData.create(file);
        AL10.alBufferData(buffer, waveData.format, waveData.data, waveData.samplerate);
        waveData.dispose();
        return buffer;
    }

    public void playRandom(boolean repeat)
    {
        Random random = new Random();
        int index = random.nextInt(Music.values().length);
        play(Music.values()[index], repeat);
    }

    public void play(Music music, boolean repeat)
    {
        //if (true)
        //    return;

        stopAll();

        int buffer = musicMap.get(music);
        AL10.alSourcei(musicSourceId, AL10.AL_BUFFER, buffer);
        AL10.alSourcei(musicSourceId, AL10.AL_LOOPING, !repeat ? 0 : 1);

        AL10.alSourceQueueBuffers(musicSourceId, buffer);

        AL10.alSourcePlay(musicSourceId);
        playing = true;
    }

    public void play(Sound sound)
    {
        if (!mutedSound)
        {
            stopSound();
            int buffer = soundMap.get(sound);
            AL10.alSourcei(soundSourceId, AL10.AL_BUFFER, buffer);

            AL10.alSourceQueueBuffers(soundSourceId, buffer);

            AL10.alSourcePlay(soundSourceId);
        }
    }

    public void stopAll()
    {
        playing = false;
        AL10.alSourceUnqueueBuffers(musicSourceId);
        AL10.alSourceStop(musicSourceId);
        //init();{
        musicSourceId = AL10.alGenSources();
        soundSourceId = AL10.alGenSources();
    }

    public void stopSound()
    {
        AL10.alSourceUnqueueBuffers(soundSourceId);
        AL10.alSourceStop(soundSourceId);
        soundSourceId = AL10.alGenSources();
    }

    public void cleanUp()
    {
        for (int buffer : musicMap.values())
            AL10.alDeleteBuffers(buffer);
        AL10.alDeleteSources(musicSourceId);
        AL10.alDeleteSources(soundSourceId);
        AL.destroy();
    }
}
