package dcdmod.Card.Common;

import java.util.ArrayList;
import java.util.List;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
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
import dcdmod.Actions.FaizGearAction;
import dcdmod.Patches.AbstractCardEnum;
import dcdmod.Patches.AbstractCustomCardWithType;



public class Faiz_Edge extends AbstractCustomCardWithType{
	
	public static final String ID = "Faiz_Edge";
	private static final CardStrings cardStrings;
	public static final String NAME;
	public static final	String DESCRIPTION;
	public static final	String [] EXTENDED_DESCRIPTION;
	public static final String IMG_PATH = "img/cards/Faiz_Edge.png";
	private static final int COST = 1;
	private static final int ATTACK_DMG = 9;
	private static final int MAGIC_NUM = 2;
	private List<TooltipInfo> tips;
	
	
	public Faiz_Edge() {
		super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
        		AbstractCard.CardType.ATTACK, AbstractCardEnum.DCD,
        		AbstractCard.CardRarity.COMMON, AbstractCard.CardTarget.ENEMY,CardColorType.Faiz);
		this.tags.add(DCDmod.RiderCard);
		this.baseDamage = ATTACK_DMG;
		this.baseMagicNumber = this.magicNumber = MAGIC_NUM;
		this.tips = new ArrayList<>();
		this.tips.add(new TooltipInfo(EXTENDED_DESCRIPTION[1], EXTENDED_DESCRIPTION[2]));
	}
	
	@Override
    public void use(AbstractPlayer p, AbstractMonster m) {
		AbstractDungeon.actionManager.addToBottom(new DamageAction(m,new DamageInfo(p, this.damage,  DamageType.NORMAL), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
		if(p.hasPower("KamenRideFaizPower")){
			FaizGearAction.FaizPoint += this.magicNumber;
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
        return new Faiz_Edge();
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
		this.rawDescription = EXTENDED_DESCRIPTION[0];
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
            this.upgradeDamage(3);
        }
	
    }
    
    static {
    	cardStrings = CardCrawlGame.languagePack.getCardStrings("Faiz_Edge");
        NAME = Faiz_Edge.cardStrings.NAME;
        DESCRIPTION = Faiz_Edge.cardStrings.DESCRIPTION;
        EXTENDED_DESCRIPTION = Faiz_Edge.cardStrings.EXTENDED_DESCRIPTION;
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
