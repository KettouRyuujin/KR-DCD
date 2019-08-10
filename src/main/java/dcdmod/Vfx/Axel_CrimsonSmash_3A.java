package dcdmod.Vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import dcdmod.Actions.EnterButtonAction;
import dcdmod.Actions.ReturnRandomNumberAction;
import dcdmod.Patches.AbstractAnimation;

public class Axel_CrimsonSmash_3A extends AbstractGameEffect {
	
	static boolean A3 = true;
	private float x;
	private float y;
	private boolean Attack = true;
	private boolean Start = true;
	private boolean Second = true;
	private AbstractCreature m;
	private int damage;
	private boolean Animation = true;
	private AbstractAnimation AXEL3;

	Axel_CrimsonSmash_3A(int d) {
		this.damage = d;
		this.m = AbstractDungeon.getMonsters().getRandomMonster(true);
		if(m != null) {
			this.x = m.drawX;
			this.y = m.drawY;
		}
		this.duration = 2.0F;//倒数时间
		this.startingDuration = 2.0F;//持续时间
	}

	public void update() {
		if(Animation) {
			String AXEL_ATLAS = "img/char/DCD_Animation/faiz_Axel/Axel_CrimsonSmash.atlas";
			String AXEL_JSON1 = "img/char/DCD_Animation/faiz_Axel/Axel_CrimsonSmash_3A.json";
			AXEL3 =  new AbstractAnimation(AXEL_ATLAS, AXEL_JSON1, 0.8f, x, y, 120.0F * Settings.scale, 120.0F * Settings.scale, 1.0f , 1.0f);
			Animation = false;
		}
		if(AXEL3!=null) {
			AXEL3.setMovable(false);
		}
		this.duration -= Gdx.graphics.getDeltaTime();
		if (this.duration < 2.0F) {
			if(Start) {
				//AbstractAnimation.changeAnimation(AXEL3, FaizAnimationAction.axel_FAR_3A);
				AXEL3.state.setAnimation(0, "3A", false);
	        	Start = false;
			}
		}
		if(this.duration < 1.7F) {
			if(Attack) {
	        	for(int i=0;i<5;i++) {
	        		AbstractDungeon.actionManager.addToBottom(new DamageAction(m,new DamageInfo(AbstractDungeon.player, this.damage, DamageType.HP_LOSS), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
	        	}
	        	Attack = false;
			}
		}
		if (this.duration < 1.5F) {
			if(Second) {
				AbstractDungeon.actionManager.addToBottom(new DamageAction(m,new DamageInfo(AbstractDungeon.player, 5, DamageType.NORMAL), AbstractGameAction.AttackEffect.BLUNT_HEAVY));	
				Second = false;
			}
		}
		if (this.duration < 0.3F) {
			this.isDone = true;
			A3=true;
        	int n = ReturnRandomNumberAction.ReturnRandomNumber();
			if(EnterButtonAction.PointerPoint != 0 && m != null) {
				if(n<=5&&Axel_CrimsonSmash_1A.A1) {
					AbstractDungeon.actionManager.addToTop(new VFXAction(new Axel_CrimsonSmash_1A(damage), 0F));
					Axel_CrimsonSmash_1A.A1 = false;
					EnterButtonAction.PointerPoint -= 1;
				}
				else if(Axel_CrimsonSmash_2A.A2){
					AbstractDungeon.actionManager.addToTop(new VFXAction(new Axel_CrimsonSmash_2A(damage), 0F));
					Axel_CrimsonSmash_2A.A2 = false;
					EnterButtonAction.PointerPoint -= 1;
				}
				else {
					AbstractDungeon.actionManager.addToTop(new VFXAction(new Axel_CrimsonSmash_3A(damage), 0F));
					Axel_CrimsonSmash_3A.A3 = false;
					EnterButtonAction.PointerPoint -= 1;
				}
			}
		}
	}

	public void render(SpriteBatch sb) {
	}

	public void dispose() {
	}
}