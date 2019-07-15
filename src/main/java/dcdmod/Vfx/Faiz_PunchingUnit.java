package dcdmod.Vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.RemoveAllBlockAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;

import dcdmod.DCDmod;
import dcdmod.Actions.FaizAnimationAction;
import dcdmod.Characters.Decade;
import dcdmod.Patches.AbstractAnimation;
import com.badlogic.gdx.graphics.Color;

public class Faiz_PunchingUnit extends AbstractGameEffect {
	
	private float x;
	private float y;
	private Texture img = null;
	boolean OnePunch = true;
	boolean TwoKick = true;
	boolean Sound = true;
	boolean ThreePunchingUnit = true;
	boolean Final = true;
	boolean Start = true;
	private AbstractCreature m;
	private int damage;
    public static String FAIZ_ATLAS = "img/char/DCD_Animational/faiz/faiz_FAR3.atlas";
    public static String FAIZ_JSON1 = "img/char/DCD_Animational/faiz/faiz_FAR3.json";
    
	public Faiz_PunchingUnit(float x, float y,AbstractCreature m,int d) {
		if (this.img == null) {
			this.img =new Texture(Gdx.files.internal("img/1024/orb-dark.png"));
		}
		this.damage = d;
		this.m = m;
		this.x = x;
		this.y = y;
		this.duration = 4.0F;//倒数时间
		this.startingDuration = 4.0F;//持续时间
		this.color = Color.WHITE.cpy();
		if(!DCDmod.AnimationTrigger) {
			new AbstractAnimation("FAIZ_FAR",FAIZ_ATLAS,FAIZ_JSON1, 0.8f, x, y, 120.0F * Settings.scale, 120.0F * Settings.scale, 1.0f);
		}
	}

	public void update() {
		if(!DCDmod.AnimationTrigger && AbstractDungeon.player.hasPower("KamenRideFaizPower")) {
			AbstractAnimation FAR1 =  AbstractAnimation.getAnimation("FAIZ_FAR");
			if(FAR1!=null) {
				FAR1.setMovable(false);
			}
			this.duration -= Gdx.graphics.getDeltaTime();
			if (this.duration < 3.5F) {
				if(Start) {
					AbstractAnimation.changeAnimation(FAR1, FaizAnimationAction.faiz_punchingunit);
					FAR1.state.setAnimation(0, "punchingunit", false);
		        	Start = false;
		        	AbstractDungeon.actionManager.addToBottom(new DamageAction(m,new DamageInfo(AbstractDungeon.player, 4, DamageType.NORMAL), AbstractGameAction.AttackEffect.BLUNT_HEAVY));	
				}
			}
			if (this.duration < 3.1F) {
				if(OnePunch) {
					AbstractDungeon.actionManager.addToBottom(new DamageAction(m,new DamageInfo(AbstractDungeon.player, 4, DamageType.NORMAL), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
		        	OnePunch = false;
				}
			}
			if (this.duration < 2.75F) {
				if(TwoKick) {
					AbstractDungeon.actionManager.addToBottom(new DamageAction(m,new DamageInfo(AbstractDungeon.player, 4, DamageType.NORMAL), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
		        	TwoKick = false;
				}
			}
			if (this.duration < 2.5F) {
				if(Sound) {
					//蓄力的声音？
		        	Sound = false;
				}
			}
			if (this.duration < 1.8F) {
				if(ThreePunchingUnit) {
					AbstractDungeon.actionManager.addToBottom(new RemoveAllBlockAction(m, AbstractDungeon.player));
					AbstractDungeon.actionManager.addToBottom(new DamageAction(m,new DamageInfo(AbstractDungeon.player, this.damage, DamageType.NORMAL), AbstractGameAction.AttackEffect.SMASH));	
					ThreePunchingUnit = false;
				}
			}
			if (this.duration < 0.9F) {
				if(Final) {
					AbstractDungeon.actionManager.addToBottom(new DamageAction(m,new DamageInfo(AbstractDungeon.player, 5, DamageType.NORMAL), AbstractGameAction.AttackEffect.BLUNT_HEAVY));	
					Final = false;
				}
			}
			if (this.duration < 0.3F) {
				this.isDone = true;
				if(Decade.cf != 3) {
					final Decade Decade = (Decade)AbstractDungeon.player;
					Decade.Trickster(34);//恢复站姿
				}
			}
		}
		else {
			this.duration -= Gdx.graphics.getDeltaTime();
			if (this.duration < 3.5F) {
				if(Start) {
		        	Start = false;
		        	for(int i = 0;i<3;i++){
		        		AbstractDungeon.actionManager.addToBottom(new DamageAction(m,new DamageInfo(AbstractDungeon.player, 4, DamageType.NORMAL), AbstractGameAction.AttackEffect.BLUNT_HEAVY));	
		        	}
					AbstractDungeon.actionManager.addToBottom(new RemoveAllBlockAction(m, AbstractDungeon.player));
					AbstractDungeon.actionManager.addToBottom(new DamageAction(m,new DamageInfo(AbstractDungeon.player, this.damage, DamageType.NORMAL), AbstractGameAction.AttackEffect.SMASH));	
					AbstractDungeon.actionManager.addToBottom(new DamageAction(m,new DamageInfo(AbstractDungeon.player, 5, DamageType.NORMAL), AbstractGameAction.AttackEffect.BLUNT_HEAVY));	
					this.isDone = true;
					ThreePunchingUnit = false;
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