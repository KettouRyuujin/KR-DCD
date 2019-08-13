package dcdmod.Vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import dcdmod.Actions.EnterButtonAction;
import dcdmod.Actions.ReturnRandomNumberAction;

public class Axel_CrimsonSmash_Timer extends AbstractGameEffect {

	private int damage;


	public Axel_CrimsonSmash_Timer(int d) {
		this.damage = d;
		this.duration = 30.0F;//倒数时间
		this.startingDuration = 30.0F;//持续时间
	}

	public void update() {
		this.duration -= Gdx.graphics.getDeltaTime();
		if(!EnterButtonAction.AxelForm) {
			this.isDone = true;
			int n = ReturnRandomNumberAction.ReturnRandomNumber();
			if(EnterButtonAction.PointerPoint != 0) {
				if(n<=3) {
					AbstractDungeon.actionManager.addToTop(new VFXAction(new Axel_CrimsonSmash_1A(damage), 0F));
					EnterButtonAction.PointerPoint -= 1;
					Axel_CrimsonSmash_1A.A1=false;
				}
				else if(n <= 6) {
					AbstractDungeon.actionManager.addToTop(new VFXAction(new Axel_CrimsonSmash_2A(damage), 0F));
					EnterButtonAction.PointerPoint -= 1;
					Axel_CrimsonSmash_2A.A2=false;
				}
				else {
					AbstractDungeon.actionManager.addToTop(new VFXAction(new Axel_CrimsonSmash_3A(damage), 0F));
					EnterButtonAction.PointerPoint -= 1;
					Axel_CrimsonSmash_3A.A3=false;
				}
			}
		}
		if (this.duration < 20.0F) {
			this.isDone = true;
			int n = ReturnRandomNumberAction.ReturnRandomNumber();
			if(EnterButtonAction.PointerPoint != 0) {
				if(n<=3) {
					AbstractDungeon.actionManager.addToTop(new VFXAction(new Axel_CrimsonSmash_1A(damage), 0F));
					EnterButtonAction.PointerPoint -= 1;
					Axel_CrimsonSmash_1A.A1=false;
				}
				else if(n <= 6) {
					AbstractDungeon.actionManager.addToTop(new VFXAction(new Axel_CrimsonSmash_2A(damage), 0F));
					EnterButtonAction.PointerPoint -= 1;
					Axel_CrimsonSmash_2A.A2=false;
				}
				else {
					AbstractDungeon.actionManager.addToTop(new VFXAction(new Axel_CrimsonSmash_3A(damage), 0F));
					EnterButtonAction.PointerPoint -= 1;
					Axel_CrimsonSmash_3A.A3=false;
				}
			}
		}
	}

	public void render(SpriteBatch sb) {

	}

	public void dispose() {
	}
}