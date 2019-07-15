package dcdmod.Vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

import dcdmod.Characters.Decade;

import com.badlogic.gdx.graphics.Color;

public class Kabuto_MaskedToRider extends AbstractGameEffect {
	private float x;
	private float y;
	private Texture img = null;
	boolean start = true;

	public Kabuto_MaskedToRider(float x, float y) {
		if (this.img == null) {
			this.img =new Texture(Gdx.files.internal("img/1024/orb-dark.png"));
		}

		this.x = x- (float)this.img.getWidth() / 2.0F;
		this.y = y;
		this.duration = 1.4F;//倒数时间
		this.startingDuration = 1.4F;//持续时间
		this.color = Color.WHITE.cpy();
	}

	public void update() {
		this.duration -= Gdx.graphics.getDeltaTime();
		if (this.duration < 0.0F) {
			final Decade Decade = (Decade)AbstractDungeon.player;
			Decade.Trickster(60);//切换模型
			this.isDone = true;
		}
	}

	public void render(SpriteBatch sb) {
		sb.setColor(this.color);
		sb.draw(this.img, this.x, this.y);
		if(start) {
			final Decade Decade = (Decade)AbstractDungeon.player;
			Decade.Trickster(63);//切换模型
			CardCrawlGame.sound.playA("formride", 0.0f);
			start = false;
		}
	}

	public void dispose() {
	}
}