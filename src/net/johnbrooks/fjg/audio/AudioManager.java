package net.johnbrooks.fjg.audio;

import org.lwjgl.LWJGLException;
import org.lwjgl.openal.AL;
import org.lwjgl.openal.AL10;
import org.newdawn.slick.openal.WaveData;
import java.util.HashMap;

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
    private int sourceId;

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
        init();
        loadBuffers();
    }

    private void init()
    {
        sourceId = AL10.alGenSources();
        AL10.alSourcef(sourceId, AL10.AL_GAIN, 1);
        AL10.alSourcef(sourceId, AL10.AL_PITCH, 1);
        AL10.alSource3f(sourceId, AL10.AL_POSITION, 0, 0, 0);
        AL10.alListener3f(AL10.AL_POSITION, 0, 0, 0);
        AL10.alListener3f(AL10.AL_VELOCITY, 0, 0, 0);

    }

    private void loadBuffers()
    {
        for (Music m : Music.values())
            musicMap.put(m, loadSound(m.path));
    }

    public int loadSound(String file)
    {
        int buffer = AL10.alGenBuffers();
        WaveData waveData = WaveData.create(file);
        AL10.alBufferData(buffer, waveData.format, waveData.data, waveData.samplerate);
        waveData.dispose();
        return buffer;
    }

    public void play(Music music, boolean repeat)
    {
        stopAll();

        int buffer = musicMap.get(music);
        AL10.alSourcei(sourceId, AL10.AL_BUFFER, buffer);
        AL10.alSourcei(sourceId, AL10.AL_LOOPING, !repeat ? 0 : 1);

        AL10.alSourceQueueBuffers(sourceId, buffer);

        AL10.alSourcePlay(sourceId);
    }

    public void stopAll()
    {
        AL10.alSourceUnqueueBuffers(sourceId);
        AL10.alSourceStop(sourceId);
        init();
    }

    public void cleanUp()
    {
        for (int buffer : musicMap.values())
            AL10.alDeleteBuffers(buffer);
        AL10.alDeleteSources(sourceId);
        AL.destroy();
    }
}