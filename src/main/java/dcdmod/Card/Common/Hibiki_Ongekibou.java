package dcdmod.Card.Common;

import java.util.ArrayList;
import java.util.List;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import basemod.helpers.TooltipInfo;
import dcdmod.DCDmod;
import dcdmod.Actions.ReturnRandomNumberAction;
import dcdmod.Actions.TaikoAttackAction;
import dcdmod.Characters.Decade;
import dcdmod.Patches.AbstractCardEnum;
import dcdmod.Patches.AbstractCustomCardWithType;
import dcdmod.Patches.HibikiTaikoKeyEvent;
import dcdmod.Power.HibikiBurnPower;
import dcdmod.Power.HibikiKurenaiSpecialPower;
import dcdmod.Power.HibikiRollPower;



public class Hibiki_Ongekibou extends AbstractCustomCardWithType{
	
	public static final String ID = "Hibiki_Ongekibou";
	private static final CardStrings cardStrings;
	public static final String NAME;
	public static final	String DESCRIPTION;
	public static final String[] EXTENDED_DESCRIPTION;
	public static final String IMG_PATH = "img/cards/Hibiki_Ongekibou.png";
	private static final int COST = 1;
	private static final int ATTACK_DMG = 5;
	private List<TooltipInfo> tips;
	private List<TooltipInfo> tips2;
	
	public Hibiki_Ongekibou() {
		super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
        		AbstractCard.CardType.ATTACK, AbstractCardEnum.DCD,
        		AbstractCard.CardRarity.COMMON, AbstractCard.CardTarget.ENEMY,CardColorType.Hibiki);
		this.tags.add(DCDmod.RiderCard);
		this.baseDamage = ATTACK_DMG;
		this.baseMagicNumber = this.magicNumber = 1;
		this.damageType = DamageType.NORMAL;
		this.tips = new ArrayList<TooltipInfo>();
		this.tips.add(new TooltipInfo(EXTENDED_DESCRIPTION[1], EXTENDED_DESCRIPTION[2]));
		this.tips2 = new ArrayList<TooltipInfo>();
		this.tips2.add(new TooltipInfo(EXTENDED_DESCRIPTION[1], EXTENDED_DESCRIPTION[3]));
	}
	
	@Override
    public void use(AbstractPlayer p, AbstractMonster m) {
		AbstractDungeon.actionManager.addToBottom(new DamageAction(m,new DamageInfo(p, this.damage, DamageType.NORMAL), AbstractGameAction.AttackEffect.FIRE));
		AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, p, new HibikiBurnPower(m, this.magicNumber, p), this.magicNumber));
		if(p.hasPower("KamnRideHibikiPower")&&!p.hasPower("HibikiKurenaiPower")) {
			HibikiTaikoKeyEvent.ComboPoint += 1;
			AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new HibikiRollPower(p, 1), 1));
		}
		else if(p.hasPower("HibikiKurenaiPower")) {
			if(ReturnRandomNumberAction.ReturnRandomNumber()>5.0D) {
				if(ReturnRandomNumberAction.ReturnRandomNumber()>5.0D) {
					AbstractDungeon.actionManager.addToBottom(new TaikoAttackAction());
				}
				else {
					AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new HibikiKurenaiSpecialPower(p, this.damage), this.damage));
				}
			}
		}
	}
	
	@Override
	public void calculateCardDamage(AbstractMonster arg0)
	{
		super.calculateCardDamage(arg0);
		if(AbstractDungeon.player.hasPower("BladeSlashPower")) {
			this.damage += AbstractDungeon.player.getPower("BladeSlashPower").amount * 2;
			this.isDamageModified = true;
		}
 		if(AbstractDungeon.player.hasPower("HibikiKurenaiSpecialPower")) {
 			this.damage += AbstractDungeon.player.getPower("HibikiKurenaiSpecialPower").amount;
 			this.isDamageModified = true;
 		}
	}
	
	@Override
    public AbstractCard makeCopy() {
        return new Hibiki_Ongekibou();
    }
	
	@Override
	public List<TooltipInfo> getCustomTooltips() {
		if(AbstractDungeon.player instanceof Decade && AbstractDungeon.player.hasPower("KamnRideHibikiPower") && AbstractDungeon.player.hasPower("HibikiKurenaiPower")) {
			return this.tips2;
		}
		return this.tips;
	}
	
	@Override
	public void optionDecade() {
		this.rawDescription = DESCRIPTION;
        initializeDescription();
	}

	@Override
	public void optionKuuga() {
		this.rawDescription = DESCRIPTION;
        initializeDescription();
	}

	@Override
	public void optionAgito() {
		this.rawDescription = DESCRIPTION;
        initializeDescription();
	}

	@Override
	public void optionRyuki() {
		this.rawDescription = DESCRIPTION;
        initializeDescription();
	}

	@Override
	public void optionFaiz() {
		this.rawDescription = DESCRIPTION;
        initializeDescription();
	}

	@Override
	public void optionBlade() {
		this.rawDescription = DESCRIPTION;
        initializeDescription();
	}

	@Override
	public void optionHibiki() {
		if(AbstractDungeon.player.hasPower("KamnRideHibikiPower")&&!AbstractDungeon.player.hasPower("HibikiKurenaiPower")) {
			this.rawDescription = EXTENDED_DESCRIPTION[0];
		}
		else if(AbstractDungeon.player.hasPower("HibikiKurenaiPower")) {
			this.rawDescription = DESCRIPTION;
		}
        initializeDescription();
	}

	@Override
	public void optionKabuto() {
		this.rawDescription = DESCRIPTION;
        initializeDescription();
	}

	@Override
	public void optionDenO() {
		this.rawDescription = DESCRIPTION;
        initializeDescription();
	}

	@Override
	public void optionKiva() {
		this.rawDescription = DESCRIPTION;
        initializeDescription();
	}

	@Override
	public void optionNeutral() {
		this.rawDescription = DESCRIPTION;
        initializeDescription();

	}
    
	@Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeDamage(3);
        }
	
    }
    
    static {
    	cardStrings = CardCrawlGame.languagePack.getCardStrings("Hibiki_Ongekibou");
        NAME = Hibiki_Ongekibou.cardStrings.NAME;
        DESCRIPTION = Hibiki_Ongekibou.cardStrings.DESCRIPTION;
        EXTENDED_DESCRIPTION = Hibiki_Ongekibou.cardStrings.EXTENDED_DESCRIPTION;
    }

	@Override
	public void energychange() {
		if(this.freeToPlayOnce){
			setBannerTexture(DCDmod.COMMON[0], DCDmod.COMMON_P[0]);
		}
		else if(this.costForTurn == -1 || this.costForTurn > 5) {
			setBannerTexture(DCDmod.COMMON[6], DCDmod.COMMON_P[6]);
		}
		else {
			int cost = this.costForTurn;
			setBannerTexture(DCDmod.COMMON[cost], DCDmod.COMMON_P[cost]);
		}
	}

	
	
}
