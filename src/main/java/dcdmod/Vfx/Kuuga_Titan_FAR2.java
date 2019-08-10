package dcdmod.Vfx;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import dcdmod.Characters.Decade;
import dcdmod.Patches.AbstractAnimation;

public class Kuuga_Titan_FAR2 extends AbstractGameEffect {

	private boolean start1 = true;
	private AbstractCreature source, target;
	private Vector2 start;
	private final int damage;
	private int stage;

	Kuuga_Titan_FAR2(AbstractCreature source, AbstractCreature target, int x, float drawX, float drawY) {
		this.duration = 99.0F;//倒数时间
		this.startingDuration = 99.0F;//持续时间
		this.source = source;
		this.target = target;
		this.damage = x;
		this.start = new Vector2(drawX, drawY);
		this.stage = 0;
	}

	public void update() {
		this.duration -= Gdx.graphics.getDeltaTime();

		AbstractAnimation titan_far;
		if(this.duration < this.startingDuration && start1) {
			final Decade Decade = (Decade) AbstractDungeon.player;
			Decade.Trickster(121);//中途切换模型
			String TITAN_FAR_ATLAS = "img/char/DCD_Animation/kuuga/titan/titan_FAR_2and3.atlas";
			String TITAN_FAR_JSON = "img/char/DCD_Animation/kuuga/titan/titan_FAR_2and3_FAR2-2.json";
			new AbstractAnimation("titan", TITAN_FAR_ATLAS, TITAN_FAR_JSON, 0.8f, this.source.drawX, this.source.drawY, this.source.hb_w, this.source.hb_h, 1.0f);
			titan_far = AbstractAnimation.getAnimation("titan");
			titan_far.setMovable(false);
			titan_far.state.setAnimation(0, "FAR2-2", false);
			start1 = false;
			stage ++;
		}
		if(this.duration < this.startingDuration -0.45F && stage == 1){
			if(this.source.hasPower("RisingMightyPower")){
				AbstractDungeon.effectsQueue.add(new Kuuga_Titan_MonsterAttacked(this.target));
				AbstractDungeon.actionManager.addToBottom(new DamageAction(this.target,new DamageInfo(this.source, this.damage, DamageInfo.DamageType.NORMAL)));
				stage ++;
			}
			else{
				AbstractDungeon.effectsQueue.add(new Kuuga_Titan_MonsterAttacked(this.target));
				stage ++;
			}
		}
		else if(this.duration < this.startingDuration - 0.95F && stage == 2){
			AbstractDungeon.actionManager.addToBottom(new VFXAction(new Kuuga_SpecialPower(this.target)));
			stage ++;
		}
		else if(this.duration < this.startingDuration - 1.41F && stage == 3){
			if(!this.source.hasPower("RisingMightyPower")){
				AbstractDungeon.actionManager.addToBottom(new VFXAction(new Kuuga_Boom(this.target , true)));
				AbstractDungeon.actionManager.addToBottom(new DamageAction(this.target,new DamageInfo(this.source, this.damage, DamageInfo.DamageType.NORMAL)));
				AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.target, this.source, "KuugaSpecialPower"));
			}
			stage ++;
		}
		else if(this.duration < this.startingDuration - 1.45F &&  this.stage == 4 ){
			if(this.source.hasPower("RisingMightyPower")){
				AbstractAnimation.clear("titan");
				String TITAN_FAR_ATLAS = "img/char/DCD_Animation/kuuga/titan/titan_FAR_2and3.atlas";
				String TITAN_FAR_JSON = "img/char/DCD_Animation/kuuga/titan/titan_FAR_2and3_FAR3-2.json";
				new AbstractAnimation("titan", TITAN_FAR_ATLAS, TITAN_FAR_JSON, 0.8f, this.source.drawX, this.source.drawY, this.source.hb_w, this.source.hb_h, 1.0f);
				titan_far = AbstractAnimation.getAnimation("titan");
				titan_far.setMovable(false);
				titan_far.state.setAnimation(0, "FAR3-2", false);
				final Decade Decade = (Decade) AbstractDungeon.player;
				Decade.Trickster(124);//中途切换模型
				stage ++;
			}
			else {
				AbstractAnimation.clear("titan");
				String TITAN_FAR_ATLAS = "img/char/DCD_Animation/kuuga/titan/titan_FAR_2and3.atlas";
				String TITAN_FAR_JSON = "img/char/DCD_Animation/kuuga/titan/titan_FAR_2and3_FAR3-1.json";
				new AbstractAnimation("titan", TITAN_FAR_ATLAS, TITAN_FAR_JSON, 0.8f, this.source.drawX, this.source.drawY, this.source.hb_w, this.source.hb_h, 1.0f);
				titan_far = AbstractAnimation.getAnimation("titan");
				titan_far.setMovable(false);
				titan_far.state.setAnimation(0, "FAR3-1", false);
				final Decade Decade = (Decade) AbstractDungeon.player;
				Decade.Trickster(123);//中途切换模型
				stage ++;
			}
		}
		else if(this.duration < this.startingDuration - 1.51F &&  this.stage == 5 && !this.source.hasPower("RisingMightyPower")){
			AbstractAnimation.clear("titan");
			String TITAN_FAR_ATLAS = "img/char/DCD_Animation/kuuga/titan/titan_FAR_1and4.atlas";
			String TITAN_FAR_JSON = "img/char/DCD_Animation/kuuga/titan/titan_FAR_1and4_FAR4-1.json";
			new AbstractAnimation("titan", TITAN_FAR_ATLAS, TITAN_FAR_JSON, 0.8f, this.source.drawX, this.source.drawY, this.source.hb_w, this.source.hb_h, 1.0f);
			titan_far = AbstractAnimation.getAnimation("titan");
			titan_far.setMovable(false);
			titan_far.state.setAnimation(0, "FAR4-1", false);
			final Decade Decade = (Decade) AbstractDungeon.player;
			Decade.Trickster(119);//中途切换模型
			this.stage ++;
		}
		else if(this.duration < this.startingDuration - 1.85F &&  this.stage == 5 && this.source.hasPower("RisingMightyPower")){
			AbstractDungeon.actionManager.addToBottom(new VFXAction(new Kuuga_Boom(this.target , true)));
			AbstractDungeon.actionManager.addToBottom(new DamageAction(this.target,new DamageInfo(this.source, this.damage, DamageInfo.DamageType.NORMAL)));
			AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.target, this.source, "KuugaSpecialPower"));
			this.stage ++;
		}
		else if(this.duration < this.startingDuration - 2.01F && this.stage == 6){
			if(this.source.hasPower("RisingMightyPower")){
				AbstractAnimation.clear("titan");
				String TITAN_FAR_ATLAS = "img/char/DCD_Animation/kuuga/titan/titan_FAR_1and4.atlas";
				String TITAN_FAR_JSON = "img/char/DCD_Animation/kuuga/titan/titan_FAR_1and4_FAR4-2.json";
				new AbstractAnimation("titan", TITAN_FAR_ATLAS, TITAN_FAR_JSON, 0.8f, this.source.drawX, this.source.drawY, this.source.hb_w, this.source.hb_h, 1.0f);
				titan_far = AbstractAnimation.getAnimation("titan");
				titan_far.setMovable(false);
				titan_far.state.setAnimation(0, "FAR4-2", false);
				final Decade Decade = (Decade) AbstractDungeon.player;
				Decade.Trickster(120);//中途切换模型
				this.stage ++;
			}
			else{
				this.isDone = true;
				AbstractAnimation.clear("titan");
				final Decade Decade = (Decade)AbstractDungeon.player;
				Decade.Trickster(4);//时间结束后切换回原本模型
				this.source.drawX = this.start.x;
				this.source.drawY = this.start.y;
			}

		}
		else if(this.duration < this.startingDuration - 2.51F && this.stage == 7){
			this.isDone = true;
			AbstractAnimation.clear("titan");
			final Decade Decade = (Decade)AbstractDungeon.player;
			Decade.Trickster(4);//时间结束后切换回原本模型
			this.source.drawX = this.start.x;
			this.source.drawY = this.start.y;
		}
	}

	public void render(SpriteBatch sb) { }

	public void dispose() { }
}