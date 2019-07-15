package dcdmod.Vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import dcdmod.Actions.EnterButtonAction;
import dcdmod.Actions.ReturnRandomNumberAction;
import com.badlogic.gdx.graphics.Color;

public class Axel_CrimsonSmash_Timer extends AbstractGameEffect {
	
	private float x;
	private float y;
	private Texture img = null;
	private AbstractCreature m;
	private int damage;
	boolean Start= true;
	boolean Animation = true;

	public Axel_CrimsonSmash_Timer(int d) {
		if (this.img == null) {
			this.img =new Texture(Gdx.files.internal("img/1024/orb-dark.png"));
		}
		this.damage = d;
		this.m = AbstractDungeon.getMonsters().getRandomMonster(true);
		this.x = m.drawX;
		this.y = m.drawY;
		this.duration = 30.0F;//倒数时间
		this.startingDuration = 30.0F;//持续时间
		this.color = Color.WHITE.cpy();
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
				else if(n>3 && n<=6) {
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
				else if(n>3 && n<=6) {
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
		sb.setColor(this.color);
		sb.draw(this.img, this.x, this.y);
	}

	public void dispose() {
	}
}