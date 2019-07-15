package dcdmod.Vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.badlogic.gdx.graphics.Color;

public class Blade_FAR_sounds1 extends AbstractGameEffect {
	private float x;
	private float y;
	private Texture img = null;
	boolean FAR = true;
	
	public Blade_FAR_sounds1(float x, float y) {
		if (this.img == null) {
			this.img =new Texture(Gdx.files.internal("img/1024/orb-dark.png"));
		}
		this.x = x- (float)this.img.getWidth() / 2.0F;
		this.y = y;
		this.duration = 4.0F;//倒数时间
		this.startingDuration = 4.0F;//持续时间
		this.color = Color.WHITE.cpy();
	}

	public void update() {
		this.duration -= Gdx.graphics.getDeltaTime();
		if (this.duration < 1.0F) {
			if(FAR) {
				CardCrawlGame.sound.playA("FAR_BLADE", 0.0f);
				FAR = false;
			}
		}
		if(this.duration < 0.0F) {
			this.isDone = true;
			CardCrawlGame.sound.playA("blade_LightningSlash", 0.0f);
		}
	}

	public void render(SpriteBatch sb) {
		sb.setColor(this.color);
		sb.draw(this.img, this.x, this.y);
	}

	public void dispose() {
	}
}