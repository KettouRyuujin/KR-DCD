package dcdmod.Vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import dcdmod.Characters.Decade;

public class Decade_attack extends AbstractGameEffect {

	private boolean start = true;

	public Decade_attack() {
		this.duration = 0.25F;//倒数时间
		this.startingDuration = 0.25F;//持续时间

	}

	public void update() {
		this.duration -= Gdx.graphics.getDeltaTime();

		if(this.duration < this.startingDuration && start) {
			final Decade Decade = (Decade)AbstractDungeon.player;
			Decade.Trickster(76);//切换模型
			start = false;
		}

		if (this.duration < 0.0F) {
			this.isDone = true;
			final Decade Decade = (Decade)AbstractDungeon.player;
			Decade.Trickster(1);//时间结束后切换回原本模型
		}
	}

	public void render(SpriteBatch sb) { }

	public void dispose() { }
}