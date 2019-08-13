package dcdmod.Vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import dcdmod.Characters.Decade;
import dcdmod.DCDmod;
import dcdmod.Helper.SpecialTaikoEffects;

public class Hibiki_taikoaction extends AbstractGameEffect {

	private boolean Start = true;
	private boolean End = true;
	
	public Hibiki_taikoaction() {

		this.duration = 1.0F;//倒数时间
		this.startingDuration = 1.0F;//持续时间

	}

	public void update() {
		this.duration -= Gdx.graphics.getDeltaTime();
		if (this.duration < 0.2F) {
			if(End){
				if(Decade.cf != 49 && Decade.cf != 3 && Decade.cf != 54) {
					final Decade Decade = (Decade)AbstractDungeon.player;
					Decade.Trickster(53);//时间结束后切换回原本模型
				}
				End = false;
			}
		}
		if(this.duration < 0.0F) {
			if(SpecialTaikoEffects.a != 4 && !DCDmod.AnimationTrigger) {
				SpecialTaikoEffects.a = 3;
				SpecialTaikoEffects.update();
			}
			this.isDone = true;
		}
	}

	public void render(SpriteBatch sb) {
		if(Start) {
			final Decade Decade = (Decade)AbstractDungeon.player;
			Decade.Trickster(52);//切换模型
			if(!DCDmod.AnimationTrigger) {
				SpecialTaikoEffects.a = 2;
				SpecialTaikoEffects.update();
			}
			Start = false;
		}
	}

	public void dispose() {
	}
}