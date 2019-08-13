package dcdmod.Vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import dcdmod.Characters.Decade;

public class Ryuki_defend extends AbstractGameEffect {

	private boolean start = true;

	public Ryuki_defend() {

		this.duration = 1.65F;//倒数时间
		this.startingDuration = 1.65F;//持续时间
		this.color = Color.WHITE.cpy();
	}

	public void update() {
		this.duration -= Gdx.graphics.getDeltaTime();
		if (this.duration < 0.0F) {
			this.isDone = true;
			final Decade Decade = (Decade)AbstractDungeon.player;
			Decade.Trickster(30);//时间结束后切换回原本模型
		}
	}

	public void render(SpriteBatch sb) {

		if(start) {
			final Decade Decade = (Decade)AbstractDungeon.player;
			Decade.Trickster(24);//切换模型
			start = false;
		}
	}

	public void dispose() {
	}
}