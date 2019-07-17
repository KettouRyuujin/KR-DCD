package dcdmod.Vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import dcdmod.Characters.Decade;

public class DenO_nakerude extends AbstractGameEffect {

	private float x;
	private float y;
	private Texture img = null;
	boolean start = true;
	boolean A1 = true;

	public DenO_nakerude() {
		if (this.img == null) {
			this.img =new Texture(Gdx.files.internal("img/1024/orb-dark.png"));
		}

		this.x = AbstractDungeon.player.drawX;
		this.y = AbstractDungeon.player.drawY;
		this.duration = 2.5F;//倒数时间
		this.startingDuration = 2.5F;//持续时间
		this.color = Color.WHITE.cpy();
	}

	public void update() {
		this.duration -= Gdx.graphics.getDeltaTime();
		if (this.duration < 1.0F) {
			if(start) {
				final Decade Decade = (Decade)AbstractDungeon.player;
				Decade.Trickster(70);//切换模型
				CardCrawlGame.sound.playA("deno_nakerude", 0.0f);
				start = false;
			}
		}
		if (this.duration < 0.0F) {
			this.isDone = true;
			if(Decade.cf != 3) {
				final Decade Decade = (Decade)AbstractDungeon.player;
				Decade.Trickster(66);//时间结束后切换回原本模型
			}
		}
	}

	public void render(SpriteBatch sb) {
		sb.setColor(this.color);
		sb.draw(this.img, this.x, this.y);
		if(A1){
			CardCrawlGame.sound.playA("attackride", 0.0f);
			A1 = false;
		}
	}

	public void dispose() {
	}
}