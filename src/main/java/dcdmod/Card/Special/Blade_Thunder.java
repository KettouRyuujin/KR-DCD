package dcdmod.Card.Special;

import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.LightningEffect;
import dcdmod.DCDmod;
import dcdmod.Patches.AbstractCardEnum;
import dcdmod.Patches.AbstractCustomCardWithType;
import dcdmod.Power.BladeThunderPower;



public class Blade_Thunder extends AbstractCustomCardWithType{
	
	public static final String ID = "Blade_Thunder";
	private static final CardStrings cardStrings;
	public static final String NAME;
	public static final	String DESCRIPTION;
	public static final String IMG_PATH = "img/cards/BladeThunder.png";
	private static final int COST = 0;

	
	
	public Blade_Thunder() {
		super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
        		AbstractCard.CardType.ATTACK, AbstractCardEnum.DCD,
        		AbstractCard.CardRarity.SPECIAL, AbstractCard.CardTarget.ENEMY,CardColorType.Blade);
		this.tags.add(DCDmod.RiderCard);
		this.baseDamage = 1;
		this.baseMagicNumber = this.magicNumber = 1;
		this.exhaust = true;
	}
	
	@Override
    public void use(AbstractPlayer p, AbstractMonster m) {
		CardCrawlGame.sound.playA("blade_thunder", 0.0f);
		AbstractDungeon.actionManager.addToBottom(new SFXAction("THUNDERCLAP", 0.05F));
		AbstractDungeon.actionManager.addToBottom(new VFXAction(new LightningEffect(m.drawX, m.drawY), 0.05F));
		AbstractDungeon.actionManager.addToBottom(new DamageAction(m,new DamageInfo(p, this.damage, this.damageTypeForTurn), AttackEffect.NONE));
		if(!(p.hasPower("BladeThunderPower") && p.getPower("BladeThunderPower").amount >= 6)) {
			AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new BladeThunderPower(p, this.magicNumber), this.magicNumber));
		}
	}
	
	@Override
	public void calculateCardDamage(AbstractMonster arg0)
	{
		super.calculateCardDamage(arg0);
		if(AbstractDungeon.player.hasPower("BladeThunderPower")) {
			this.damage += AbstractDungeon.player.getPower("BladeThunderPower").amount * 2;
			this.isDamageModified = true;
		}
	}
	
	@Override
    public AbstractCard makeCopy() {
        return new Blade_Thunder();
    }
	
	@Override
	public void optionDecade() {
		setBackgroundTexture("img/512/skill_decade.png", "img/1024/skill_decade.png");
	}

	@Override
	public void optionKuuga() {
	     setBackgroundTexture("img/512/skill_kuuga.png", "img/1024/skill_kuuga.png");
	}

	@Override
	public void optionAgito() {
	    setBackgroundTexture("img/512/skill_agito.png", "img/1024/skill_agito.png");
	}

	@Override
	public void optionRyuki() {
	    setBackgroundTexture("img/512/skill_ryuki.png", "img/1024/skill_ryuki.png");
	}

	@Override
	public void optionFaiz() {
	    setBackgroundTexture("img/512/skill_faiz.png", "img/1024/skill_faiz.png");
	}

	@Override
	public void optionBlade() {
	    setBackgroundTexture("img/512/skill_blade.png", "img/1024/skill_blade.png");
	}

	@Override
	public void optionHibiki() {
	    setBackgroundTexture("img/512/skill_hibiki.png", "img/1024/skill_hibiki.png");
	}

	@Override
	public void optionKabuto() {
	    setBackgroundTexture("img/512/skill_kabuto.png", "img/1024/skill_kabuto.png");
	}

	@Override
	public void optionDenO() {
	    setBackgroundTexture("img/512/skill_deno.png", "img/1024/skill_deno.png");
	}

	@Override
	public void optionKiva() {
	    setBackgroundTexture("img/512/skill_kiva.png", "img/1024/skill_kiva.png");
	}

	@Override
	public void optionNeutral() {
		setBackgroundTexture("img/512/skill_decade.png", "img/1024/skill_decade.png");
	}
	
	@Override
    public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			this.upgradeBaseCost(0);
		}
	
    }
    
    static {
    	cardStrings = CardCrawlGame.languagePack.getCardStrings("Blade_Thunder");
        NAME = Blade_Thunder.cardStrings.NAME;
        DESCRIPTION = Blade_Thunder.cardStrings.DESCRIPTION;
    }

	@Override
	public void energychange() {
		if(this.freeToPlayOnce){
			setBannerTexture(DCDmod.SPECIAL[0], DCDmod.SPECIAL_P[0]);
		}
		else if(this.costForTurn == -1 || this.costForTurn > 5) {
			setBannerTexture(DCDmod.SPECIAL[6], DCDmod.SPECIAL_P[6]);
		}
		else {
			int cost = this.costForTurn;
			setBannerTexture(DCDmod.SPECIAL[cost], DCDmod.SPECIAL_P[cost]);
		}
	}

	
	
}
