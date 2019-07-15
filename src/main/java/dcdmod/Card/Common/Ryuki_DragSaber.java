package dcdmod.Card.Common;

import java.util.ArrayList;
import java.util.List;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.unique.RegenAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import basemod.helpers.TooltipInfo;
import dcdmod.DCDmod;
import dcdmod.Actions.RyukiAttackAction;
import dcdmod.Patches.AbstractCardEnum;
import dcdmod.Patches.AbstractCustomCardWithType;



public class Ryuki_DragSaber extends AbstractCustomCardWithType{
	
	public static final String ID = "Ryuki_DragSaber";
	private static final CardStrings cardStrings;
	public static final String NAME;
	public static final	String DESCRIPTION;
	public static final	String [] EXTENDED_DESCRIPTION;
	public static final String IMG_PATH = "img/cards/Ryuki_DragSaber.png";
	private static final int COST = 1;
	private static final int ATTACK_DMG = 8;
	private static final int BLOCK_AMT = 9;
	private static final int MAGIC_NUM = 3;
	private List<TooltipInfo> tips;
	
	
	public Ryuki_DragSaber() {
		super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
        		AbstractCard.CardType.ATTACK, AbstractCardEnum.DCD,
        		AbstractCard.CardRarity.COMMON, AbstractCard.CardTarget.ENEMY,CardColorType.Ryuki);
		this.tags.add(DCDmod.RiderCard);
		this.baseMagicNumber = this.magicNumber = MAGIC_NUM;
		this.baseDamage = ATTACK_DMG;
		this.baseBlock = BLOCK_AMT;
		this.tips = new ArrayList<TooltipInfo>();
		this.tips.add(new TooltipInfo(EXTENDED_DESCRIPTION[2], EXTENDED_DESCRIPTION[3]));
	}
	
	@Override
    public void use(AbstractPlayer p, AbstractMonster m) {
		if(p.hasPower("KamenRideRyukiPower")) {
			if(p.hasPower("DragClawPower") && !p.hasPower("DragShieldPower")) {
				AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(AbstractDungeon.player, AbstractDungeon.player, "DragClawPower"));
				AbstractDungeon.actionManager.addToBottom(new RyukiAttackAction(m, new DamageInfo(p, this.damage, this.damageTypeForTurn), 5));
			}
			else if(p.hasPower("DragShieldPower")) {
				AbstractDungeon.actionManager.addToTop(new DamageAction(m,new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
				AbstractDungeon.actionManager.addToTop(new GainBlockAction(p, p, this.block));
				AbstractDungeon.actionManager.addToBottom(new ReducePowerAction(p, p, "DragShieldPower", 1));
			}
			else {
				AbstractDungeon.actionManager.addToBottom(new DamageAction(m,new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
			}
		}
		else {
			AbstractDungeon.actionManager.addToBottom(new DamageAction(m,new DamageInfo(p, this.damage, this.damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
		}
		AbstractDungeon.actionManager.addToTop(new RegenAction(p, this.magicNumber));
	}
	
	@Override
	public void calculateCardDamage(AbstractMonster arg0)
	{
		super.calculateCardDamage(arg0);
		if(AbstractDungeon.player.hasPower("BladeSlashPower")) {
			this.damage += AbstractDungeon.player.getPower("BladeSlashPower").amount * 2;
			this.isDamageModified = true;
		}
		if(AbstractDungeon.player.hasPower("MirrorWorldPower")) {
			this.damage += 5;
			this.isDamageModified = true;
		}
		if(AbstractDungeon.player.hasPower("KamenRideRyukiPower") && AbstractDungeon.player.hasPower("DragClawPower") && !AbstractDungeon.player.hasPower("DragShieldPower")) {
			this.damage += AbstractDungeon.player.getPower("DragClawPower").amount *3;
			this.isDamageModified = true;
		}
	}
	
	@Override
	public List<TooltipInfo> getCustomTooltips() {
		return this.tips;
	}
	
	@Override
    public AbstractCard makeCopy() {
        return new Ryuki_DragSaber();
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
		if(AbstractDungeon.player.hasPower("DragClawPower") && !AbstractDungeon.player.hasPower("DragShieldPower")) {
			this.rawDescription = EXTENDED_DESCRIPTION[0];
		}
		else if(AbstractDungeon.player.hasPower("DragShieldPower")){
			this.rawDescription = EXTENDED_DESCRIPTION[1];
		}
		else {
			this.rawDescription = DESCRIPTION;
		}
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
            this.upgradeDamage(3);
        }
	
    }
    
    static {
    	cardStrings = CardCrawlGame.languagePack.getCardStrings("Ryuki_DragSaber");
        NAME = Ryuki_DragSaber.cardStrings.NAME;
        DESCRIPTION = Ryuki_DragSaber.cardStrings.DESCRIPTION;
        EXTENDED_DESCRIPTION = Ryuki_DragSaber.cardStrings.EXTENDED_DESCRIPTION;
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
