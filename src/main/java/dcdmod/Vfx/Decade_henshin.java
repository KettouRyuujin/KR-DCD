package dcdmod.Vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import dcdmod.Characters.Decade;
import com.badlogic.gdx.graphics.Color;

public class Decade_henshin extends AbstractGameEffect {
	private float x;
	private float y;
	private Texture img = null;
	boolean start = true;

	public Decade_henshin(float x, float y) {
		if (this.img == null) {
			this.img =new Texture(Gdx.files.internal("img/1024/orb-dark.png"));
		}

		this.x = x- (float)this.img.getWidth() / 2.0F;
		this.y = y;
		this.duration = 6.2F;//倒数时间
		this.startingDuration = 6.2F;//持续时间
		this.color = Color.WHITE.cpy();
	}

	public void update() {
		this.duration -= Gdx.graphics.getDeltaTime();
		if (this.duration < 0.0F) {
			this.isDone = true;
			final Decade Decade = (Decade)AbstractDungeon.player;
			Decade.Trickster(1);//时间结束后切换回原本模型
		}
	}

	public void render(SpriteBatch sb) {
		sb.setColor(this.color);
		sb.draw(this.img, this.x, this.y);
		if(start) {
			final Decade Decade = (Decade)AbstractDungeon.player;
			Decade.Trickster(2);//切换模型
			start = false;
		}
	}

	public void dispose() {
	}
}