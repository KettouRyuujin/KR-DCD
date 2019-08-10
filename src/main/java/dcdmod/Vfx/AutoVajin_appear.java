package dcdmod.Vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

import dcdmod.Helper.SpecialAutoVajin;

import com.badlogic.gdx.graphics.Color;

public class AutoVajin_appear extends AbstractGameEffect {
	private boolean start = true;

	public AutoVajin_appear(float x, float y) {

		this.duration = 2.89F;//倒数时间
		this.startingDuration = 2.89F;//持续时间
		this.color = Color.WHITE.cpy();
	}

	public void update() {
		this.duration -= Gdx.graphics.getDeltaTime();
		if (this.duration < 0.89F && start) {
			SpecialAutoVajin.a = 1;
			SpecialAutoVajin.update();
			start = false;
		}
		if(this.duration< 0.0F){
			SpecialAutoVajin.a = 5;
			SpecialAutoVajin.update();
			this.isDone = true;
		}
	}

	public void render(SpriteBatch sb) {

	}

	public void dispose() {
	}
}