package dcdmod.Vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.badlogic.gdx.graphics.Color;

public class Hibiki_taikoR2 extends AbstractGameEffect {
	private float x;
	private float y;
	private Texture img = null;

	public Hibiki_taikoR2(float x, float y) {
		if (this.img == null) {
			this.img =new Texture(Gdx.files.internal("img/char/DCD_Animation/hibiki/taiko/taikoR2.png"));
		}

		this.x = x;
		this.y = y;
		this.duration = 0.3F;//倒数时间
		this.startingDuration = 0.3F;//持续时间
		this.color = Color.WHITE.cpy();
	}

	public void update() {
		this.duration -= Gdx.graphics.getDeltaTime();
		if (this.duration < 0.0F) {
			this.isDone = true;
		}
	}

	public void render(SpriteBatch sb) {
		sb.setColor(this.color);
		sb.draw(this.img, this.x, this.y);
	}

	public void dispose() {
	}
}