package dcdmod.Vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.badlogic.gdx.graphics.Color;

public class Kabuto_FAR_sounds extends AbstractGameEffect {
	private float x;
	private float y;
	private Texture img = null;
	boolean sound =true;
	boolean Bgm =true;
	
	public Kabuto_FAR_sounds(float x, float y) {
		if (this.img == null) {
			this.img =new Texture(Gdx.files.internal("img/1024/orb-dark.png"));
		}

		this.x = x- (float)this.img.getWidth() / 2.0F;
		this.y = y;
		this.duration = 3F;//倒数时间
		this.startingDuration = 3F;//持续时间
		this.color = Color.WHITE.cpy();
	}

	public void update() {
		this.duration -= Gdx.graphics.getDeltaTime();
		if(this.duration < 1.5F&&Bgm) {
			CardCrawlGame.sound.playA("kabuto_BGM", 0.0f);
			Bgm = false;
		}
		if (this.duration < 0.0F) {
			this.isDone = true;
			CardCrawlGame.sound.playA("FAR_KABUTO", 0.0f);
		}
	}

	public void render(SpriteBatch sb) {
		sb.setColor(this.color);
		sb.draw(this.img, this.x, this.y);
		if(sound) {
			CardCrawlGame.sound.playA("FAR", 0F);
			sound = false;
		}
	}

	public void dispose() {
	}
}