package dcdmod.Patches;

import java.io.IOException;

import com.evacipated.cardcrawl.modthespire.lib.SpireConfig;
import com.megacrit.cardcrawl.core.Settings;

import basemod.interfaces.StartGameSubscriber;

public abstract class AbstractSaveLoadSubscriber implements StartGameSubscriber {
	private SpireConfig config;
	private static final String KEY = "SLSeed";
	
	private Long getSavedSeed() {
		String tmp = config.getString(KEY);
		if (tmp == null)
			return new Long(0L);
		return Long.parseLong(tmp);
	}
	
	@Override
	public void receiveStartGame() {
		if (getSavedSeed().longValue() != Settings.seed.longValue()) {
			config.setString(KEY, Settings.seed.toString());
			try {
				config.save();
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println("初次结束");
		} else {
			onLoadGame();
		}
	}

	/**
	 * 只有在继续游戏时才会触发，初次开始游戏不会触发。
	 */
	protected abstract void onLoadGame();
	
	/**
	 * 设置存档配置，必须设置，否则无法生效
	 * @param config
	 */
	protected void setConfig(SpireConfig config) {
		this.config = config;
	}
	
}
