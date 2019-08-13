package dcdmod.Vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import dcdmod.Characters.Decade;
import dcdmod.DCDmod;
import dcdmod.Helper.SpecialTaikoEffects;

public class Hibiki_taikoprepare extends AbstractGameEffect {

	private boolean Start = true;
	
	public Hibiki_taikoprepare() {
		this.duration = 1.0F;//倒数时间
		this.startingDuration = 1.0F;//持续时间
	}

	public void update() {
		this.duration -= Gdx.graphics.getDeltaTime();
		if (this.duration < 0.0F) {
			this.isDone = true;
		}
	}

	public void render(SpriteBatch sb) {
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