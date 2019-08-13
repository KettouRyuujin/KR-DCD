package dcdmod.Card.Common;

import java.util.ArrayList;
import java.util.List;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
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
import dcdmod.Actions.KunaiGunAttackAction;
import dcdmod.Actions.KunaiGunDiscardAction;
import dcdmod.Patches.AbstractCardEnum;
import dcdmod.Patches.AbstractCustomCardWithType;



public class Kabuto_KunaiGun extends AbstractCustomCardWithType{
	
	public static final String ID = "Kabuto_KunaiGun";
	private static final CardStrings cardStrings;
	public static final String NAME;
	public static final	String DESCRIPTION;
	public static final	String [] EXTENDED_DESCRIPTION;
	public static final String IMG_PATH = "img/cards/Kabuto_KunaiGun.png";
	private static final int COST = 1;
	private static final int ATTACK_DMG = 8;
	private static final int MAGIC_NUM = 1;
	private static final int BLOCK_AMT = 8;
	private List<TooltipInfo> tips;
	
	
	public Kabuto_KunaiGun() {
		super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
        		AbstractCard.CardType.ATTACK, AbstractCardEnum.DCD,
        		AbstractCard.CardRarity.COMMON, AbstractCard.CardTarget.ENEMY,CardColorType.Kabuto);
		this.tags.add(DCDmod.RiderCard);
		this.baseDamage = ATTACK_DMG;
		this.baseBlock = BLOCK_AMT;
		this.baseMagicNumber = this.magicNumber = MAGIC_NUM;
		this.tips = new ArrayList<>();
		this.tips.add(new TooltipInfo(EXTENDED_DESCRIPTION[1], EXTENDED_DESCRIPTION[2]));
	}
	
	@Override
    public void use(AbstractPlayer p, AbstractMonster m) {
		if(p.hasPower("KabutoMaskedPower")){
			AbstractDungeon.actionManager.addToBottom(new DrawCardAction(p, this.magicNumber));
			AbstractDungeon.actionManager.addToBottom(new KunaiGunDiscardAction(p, p, this.magicNumber, false));
			AbstractDungeon.actionManager.addToBottom(new DamageAction(m,new DamageInfo(p, this.damage,  DamageType.NORMAL), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
			AbstractDungeon.actionManager.addToBottom(new KunaiGunAttackAction(m, this.damage, this.block));
		}
		else {
			AbstractDungeon.actionManager.addToBottom(new DrawCardAction(p, this.magicNumber));
			AbstractDungeon.actionManager.addToBottom(new DiscardAction(p, p, this.magicNumber, false));
			AbstractDungeon.actionManager.addToBottom(new DamageAction(m,new DamageInfo(p, this.damage,  DamageType.NORMAL), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
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
        return new Kabuto_KunaiGun();
    }
	
	@Override
	public void optionDecade() {
		this.rawDescription = DESCRIPTION;
		loadCardImage("img/cards/Kabuto_KunaiGun.png");
        initializeDescription();
	}

	@Override
	public void optionKuuga() {
		this.rawDescription = DESCRIPTION;
		loadCardImage("img/cards/Kabuto_KunaiGun.png");
        initializeDescription();
	}

	@Override
	public void optionAgito() {
		this.rawDescription = DESCRIPTION;
		loadCardImage("img/cards/Kabuto_KunaiGun.png");
        initializeDescription();
	}

	@Override
	public void optionRyuki() {
		this.rawDescription = DESCRIPTION;
		loadCardImage("img/cards/Kabuto_KunaiGun.png");
        initializeDescription();
	}

	@Override
	public void optionFaiz() {
		this.rawDescription = DESCRIPTION;
		loadCardImage("img/cards/Kabuto_KunaiGun.png");
        initializeDescription();
	}

	@Override
	public void optionBlade() {
		this.rawDescription = DESCRIPTION;
		loadCardImage("img/cards/Kabuto_KunaiGun.png");
        initializeDescription();
	}

	@Override
	public void optionHibiki() {
		this.rawDescription = DESCRIPTION;
		loadCardImage("img/cards/Kabuto_KunaiGun.png");
        initializeDescription();
	}

	@Override
	public void optionKabuto() {
		if(AbstractDungeon.player.hasPower("KabutoMaskedPower")){
			this.rawDescription = EXTENDED_DESCRIPTION[0];
			loadCardImage("img/cards/Kabuto_KunaiGun.png");
		}
		else {
			this.rawDescription = DESCRIPTION;
			loadCardImage("img/cards/Kabuto_KunaiGun_k.png");
		}
        initializeDescription();
	}

	@Override
	public void optionDenO() {
		this.rawDescription = DESCRIPTION;
		loadCardImage("img/cards/Kabuto_KunaiGun.png");
        initializeDescription();
	}

	@Override
	public void optionKiva() {
		this.rawDescription = DESCRIPTION;
		loadCardImage("img/cards/Kabuto_KunaiGun.png");
        initializeDescription();
	}

	@Override
	public void optionNeutral() {
		this.rawDescription = DESCRIPTION;
		loadCardImage("img/cards/Kabuto_KunaiGun.png");
        initializeDescription();
	}
    
	@Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeMagicNumber(1);
        }
	
    }
    
    static {
    	cardStrings = CardCrawlGame.languagePack.getCardStrings("Kabuto_KunaiGun");
        NAME = Kabuto_KunaiGun.cardStrings.NAME;
        DESCRIPTION = Kabuto_KunaiGun.cardStrings.DESCRIPTION;
        EXTENDED_DESCRIPTION = Kabuto_KunaiGun.cardStrings.EXTENDED_DESCRIPTION;
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
