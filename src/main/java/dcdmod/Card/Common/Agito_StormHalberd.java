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
import dcdmod.Patches.AbstractCardEnum;
import dcdmod.Patches.AbstractCustomCardWithType;
import dcdmod.Power.StormSpecialPower;



public class Agito_StormHalberd extends AbstractCustomCardWithType{
	
	public static final String ID = "Agito_StormHalberd";
	private static final CardStrings cardStrings;
	public static final String NAME;
	public static final	String DESCRIPTION;
	public static final	String [] EXTENDED_DESCRIPTION;
	public static final String IMG_PATH = "img/cards/Agito_StormHalberd.png";
	private static final int COST = 1;
	private static final int MAGIC_NUM = 1;
	private List<TooltipInfo> tips;
	
	
	public Agito_StormHalberd() {
		super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
        		AbstractCard.CardType.ATTACK, AbstractCardEnum.DCD,
        		AbstractCard.CardRarity.COMMON, AbstractCard.CardTarget.ENEMY,CardColorType.Agito);
		this.tags.add(DCDmod.RiderCard);
		this.baseMagicNumber = this.magicNumber = MAGIC_NUM;
		this.tips = new ArrayList<>();
		this.tips.add(new TooltipInfo(EXTENDED_DESCRIPTION[1], EXTENDED_DESCRIPTION[2]));
	}
	
	@Override
    public void use(AbstractPlayer p, AbstractMonster m) {
		int x = 0;
		if(p.hasPower("Strength")) {
			x = p.getPower("Strength").amount;
		}
		AbstractDungeon.actionManager.addToBottom(new DamageAction(m,new DamageInfo(p, x*2,  DamageType.NORMAL), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
		AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new StormSpecialPower(p, this.magicNumber), this.magicNumber));
		if(p.hasPower("AgitoStormPower")) {
			boolean Storm = true;
			for(AbstractCard c : AbstractDungeon.player.discardPile.group) {
				   if(c.cardID.equals("FormRideStorm")) {
					   AbstractDungeon.player.discardPile.removeCard(c);
					   AbstractDungeon.player.hand.addToTop(c);
					   AbstractDungeon.player.hand.refreshHandLayout();
					   AbstractDungeon.player.hand.applyPowers();
					   Storm = false;
					   break;
				   }
			   }
			for(AbstractCard c : AbstractDungeon.player.drawPile.group) {
				if(c.cardID.equals("FormRideStorm") && Storm) {
					AbstractDungeon.player.drawPile.removeCard(c);
					AbstractDungeon.player.hand.addToTop(c);
					AbstractDungeon.player.hand.refreshHandLayout();
					AbstractDungeon.player.hand.applyPowers();
					Storm = false;
					break;
				}
			}
			for(AbstractCard c : AbstractDungeon.player.exhaustPile.group) {
				if(c.cardID.equals("FormRideStorm") && Storm) {
					AbstractDungeon.player.exhaustPile.removeCard(c);
					AbstractDungeon.player.hand.addToTop(c.makeCopy());
					AbstractDungeon.player.hand.refreshHandLayout();
					AbstractDungeon.player.hand.applyPowers();
					break;
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
	}
	
	@Override
	public List<TooltipInfo> getCustomTooltips() {
		return this.tips;
	}
	
	@Override
    public AbstractCard makeCopy() {
        return new Agito_StormHalberd();
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
		if(AbstractDungeon.player.hasPower("AgitoStormPower")) {
			this.rawDescription = EXTENDED_DESCRIPTION[0];
		}
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
		this.rawDescription = DESCRIPTION;
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
            this.upgradeBaseCost(0);
        }
	
    }
    
    static {
    	cardStrings = CardCrawlGame.languagePack.getCardStrings("Agito_StormHalberd");
        NAME = Agito_StormHalberd.cardStrings.NAME;
        DESCRIPTION = Agito_StormHalberd.cardStrings.DESCRIPTION;
        EXTENDED_DESCRIPTION = Agito_StormHalberd.cardStrings.EXTENDED_DESCRIPTION;
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
