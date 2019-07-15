package dcdmod.Vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

import dcdmod.DCDmod;
import dcdmod.Characters.Decade;
import dcdmod.Helper.SpecialTaikoEffects;

import com.badlogic.gdx.graphics.Color;

public class Hibiki_taikoprepare extends AbstractGameEffect {
	private float x;
	private float y;
	private Texture img = null;
	boolean Start = true;
	
	public Hibiki_taikoprepare(float x, float y) {
		if (this.img == null) {
			this.img =new Texture(Gdx.files.internal("img/1024/orb-dark.png"));
		}

		this.x = x- (float)this.img.getWidth() / 2.0F;
		this.y = y;
		this.duration = 1.0F;//倒数时间
		this.startingDuration = 1.0F;//持续时间
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
		if(Start) {
			final Decade Decade = (Decade)AbstractDungeon.player;
			Decade.Trickster(51);//切换模型
			if(!DCDmod.AnimationTrigger) {
				SpecialTaikoEffects.a = 1;
				SpecialTaikoEffects.update();
			}
			Start = false;
		}
	}

	public void dispose() {
	}
}