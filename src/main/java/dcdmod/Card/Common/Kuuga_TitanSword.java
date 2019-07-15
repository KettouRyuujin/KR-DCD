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
import dcdmod.Patches.AbstractCardEnum;
import dcdmod.Patches.AbstractCustomCardWithType;



public class Kuuga_TitanSword extends AbstractCustomCardWithType{
	
	public static final String ID = "Kuuga_TitanSword";
	private static final CardStrings cardStrings;
	public static final String NAME;
	public static final	String DESCRIPTION;
	public static final	String [] EXTENDED_DESCRIPTION;
	public static final String IMG_PATH = "img/cards/Kuuga_TitanSword.png";
	private static final int COST = 1;
	private static final int ATTACK_DMG = 12;
	private static final int MAGIC_NUM = 2;
	private List<TooltipInfo> tips;
	
	
	public Kuuga_TitanSword() {
		super(ID, NAME, IMG_PATH, COST, DESCRIPTION,
        		AbstractCard.CardType.ATTACK, AbstractCardEnum.DCD,
        		AbstractCard.CardRarity.COMMON, AbstractCard.CardTarget.ENEMY,CardColorType.Kuuga);
		this.tags.add(DCDmod.RiderCard);
		this.baseDamage = ATTACK_DMG;
		this.baseMagicNumber = this.magicNumber = MAGIC_NUM;
		this.tips = new ArrayList<TooltipInfo>();
		this.tips.add(new TooltipInfo(EXTENDED_DESCRIPTION[8], EXTENDED_DESCRIPTION[9]));
	}
	
	@Override
    public void use(AbstractPlayer p, AbstractMonster m) {
		AbstractDungeon.actionManager.addToBottom(new DamageAction(m,new DamageInfo(p, this.damage, DamageType.NORMAL), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
		if(p.hasPower("KuugaTitanPower")) {
			AbstractDungeon.actionManager.addToBottom(new DamageAction(p,new DamageInfo(p, this.magicNumber, DamageType.NORMAL), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
		}
		else if(p.hasPower("RisingTitanPower")){
			for(int i = 0;i<3;i++) {
				AbstractDungeon.actionManager.addToBottom(new DamageAction(p,new DamageInfo(p, this.magicNumber, DamageType.NORMAL), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
			}
			if(p.hasPower("RisingMightyPower")) {
				AbstractDungeon.actionManager.addToBottom(new DamageAction(m,new DamageInfo(p, this.damage, DamageType.NORMAL), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
			}
		}
		else {
			AbstractDungeon.actionManager.addToBottom(new DamageAction(p,new DamageInfo(p, this.magicNumber, DamageType.HP_LOSS), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
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
        return new Kuuga_TitanSword();
    }
	
	@Override
	public void optionDecade() {
		this.name = NAME;
		this.baseDamage = ATTACK_DMG + (AbstractDungeon.player.maxHealth - AbstractDungeon.player.currentHealth);
		this.baseMagicNumber = this.magicNumber = MAGIC_NUM;
		if(upgraded) {
			this.baseDamage = ATTACK_DMG + (AbstractDungeon.player.maxHealth - AbstractDungeon.player.currentHealth) + 2;
			this.baseMagicNumber = this.magicNumber = MAGIC_NUM -1;
		}
		this.rawDescription = DESCRIPTION;
		loadCardImage("img/cards/Kuuga_TitanSword.png");
        initializeDescription();
	}

	@Override
	public void optionKuuga() {
		this.name = NAME;
		if(AbstractDungeon.player.hasPower("KuugaTitanPower")) {
			this.baseDamage = (int) (ATTACK_DMG + (AbstractDungeon.player.currentBlock * 0.5));
			this.rawDescription = EXTENDED_DESCRIPTION[0];
			if(upgraded) {
				this.baseDamage = 20 + AbstractDungeon.player.currentBlock;
				this.baseMagicNumber = this.magicNumber = MAGIC_NUM + 3;
				this.rawDescription = EXTENDED_DESCRIPTION[1];
			}
			loadCardImage("img/cards/Kuuga_TitanSword.png");
		}
		else if(AbstractDungeon.player.hasPower("RisingTitanPower")){
			this.baseDamage = (int) ((ATTACK_DMG + 2) + (AbstractDungeon.player.currentBlock * 0.5));
			this.baseMagicNumber = this.magicNumber = MAGIC_NUM + 1;
			this.rawDescription = EXTENDED_DESCRIPTION[2];
			this.name = EXTENDED_DESCRIPTION[6];
			loadCardImage("img/cards/Rising_TitanSword.png");
			if(upgraded) {
				this.baseDamage = 22 + AbstractDungeon.player.currentBlock;
				this.baseMagicNumber = this.magicNumber = MAGIC_NUM + 2;
				this.rawDescription = EXTENDED_DESCRIPTION[3];
			}
			if(AbstractDungeon.player.hasPower("RisingMightyPower")){
				this.rawDescription = EXTENDED_DESCRIPTION[4];
				this.name = EXTENDED_DESCRIPTION[7];
				loadCardImage("img/cards/Rising_DoubleTitanSword.png");
				if(upgraded) {
					this.rawDescription = EXTENDED_DESCRIPTION[5];
				}
			}
		}
		else {
			this.baseDamage = ATTACK_DMG + (AbstractDungeon.player.maxHealth - AbstractDungeon.player.currentHealth);
			this.baseMagicNumber = this.magicNumber = MAGIC_NUM;
			if(upgraded) {
				this.baseDamage = ATTACK_DMG + (AbstractDungeon.player.maxHealth - AbstractDungeon.player.currentHealth) + 2;
				this.baseMagicNumber = this.magicNumber = MAGIC_NUM -1;
			}
			this.rawDescription = DESCRIPTION;
			loadCardImage("img/cards/Kuuga_TitanSword.png");
		}
        initializeDescription();
	}

	@Override
	public void optionAgito() {
		this.name = NAME;
		this.baseDamage = ATTACK_DMG + (AbstractDungeon.player.maxHealth - AbstractDungeon.player.currentHealth);
		this.baseMagicNumber = this.magicNumber = MAGIC_NUM;
		if(upgraded) {
			this.baseDamage = ATTACK_DMG + (AbstractDungeon.player.maxHealth - AbstractDungeon.player.currentHealth) + 2;
			this.baseMagicNumber = this.magicNumber = MAGIC_NUM -1;
		}
		this.rawDescription = DESCRIPTION;
		loadCardImage("img/cards/Kuuga_TitanSword.png");
        initializeDescription();
	}

	@Override
	public void optionRyuki() {
		this.name = NAME;
		this.baseDamage = ATTACK_DMG + (AbstractDungeon.player.maxHealth - AbstractDungeon.player.currentHealth);
		this.baseMagicNumber = this.magicNumber = MAGIC_NUM;
		if(upgraded) {
			this.baseDamage = ATTACK_DMG + (AbstractDungeon.player.maxHealth - AbstractDungeon.player.currentHealth) + 2;
			this.baseMagicNumber = this.magicNumber = MAGIC_NUM -1;
		}
		this.rawDescription = DESCRIPTION;
		loadCardImage("img/cards/Kuuga_TitanSword.png");
        initializeDescription();
	}

	@Override
	public void optionFaiz() {
		this.name = NAME;
		this.baseDamage = ATTACK_DMG + (AbstractDungeon.player.maxHealth - AbstractDungeon.player.currentHealth);
		this.baseMagicNumber = this.magicNumber = MAGIC_NUM;
		if(upgraded) {
			this.baseDamage = ATTACK_DMG + (AbstractDungeon.player.maxHealth - AbstractDungeon.player.currentHealth) + 2;
			this.baseMagicNumber = this.magicNumber = MAGIC_NUM -1;
		}
		this.rawDescription = DESCRIPTION;
		loadCardImage("img/cards/Kuuga_TitanSword.png");
        initializeDescription();
	}

	@Override
	public void optionBlade() {
		this.name = NAME;
		this.baseDamage = ATTACK_DMG + (AbstractDungeon.player.maxHealth - AbstractDungeon.player.currentHealth);
		this.baseMagicNumber = this.magicNumber = MAGIC_NUM;
		if(upgraded) {
			this.baseDamage = ATTACK_DMG + (AbstractDungeon.player.maxHealth - AbstractDungeon.player.currentHealth) + 2;
			this.baseMagicNumber = this.magicNumber = MAGIC_NUM -1;
		}
		this.rawDescription = DESCRIPTION;
		loadCardImage("img/cards/Kuuga_TitanSword.png");
        initializeDescription();
	}

	@Override
	public void optionHibiki() {
		this.name = NAME;
		this.baseDamage = ATTACK_DMG + (AbstractDungeon.player.maxHealth - AbstractDungeon.player.currentHealth);
		this.baseMagicNumber = this.magicNumber = MAGIC_NUM;
		if(upgraded) {
			this.baseDamage = ATTACK_DMG + (AbstractDungeon.player.maxHealth - AbstractDungeon.player.currentHealth) + 2;
			this.baseMagicNumber = this.magicNumber = MAGIC_NUM -1;
		}
		this.rawDescription = DESCRIPTION;
		loadCardImage("img/cards/Kuuga_TitanSword.png");
        initializeDescription();
	}

	@Override
	public void optionKabuto() {
		this.name = NAME;
		this.baseDamage = ATTACK_DMG + (AbstractDungeon.player.maxHealth - AbstractDungeon.player.currentHealth);
		this.baseMagicNumber = this.magicNumber = MAGIC_NUM;
		if(upgraded) {
			this.baseDamage = ATTACK_DMG + (AbstractDungeon.player.maxHealth - AbstractDungeon.player.currentHealth) + 2;
			this.baseMagicNumber = this.magicNumber = MAGIC_NUM -1;
		}
		this.rawDescription = DESCRIPTION;
		loadCardImage("img/cards/Kuuga_TitanSword.png");
        initializeDescription();
	}

	@Override
	public void optionDenO() {
		this.name = NAME;
		this.baseDamage = ATTACK_DMG + (AbstractDungeon.player.maxHealth - AbstractDungeon.player.currentHealth);
		this.baseMagicNumber = this.magicNumber = MAGIC_NUM;
		if(upgraded) {
			this.baseDamage = ATTACK_DMG + (AbstractDungeon.player.maxHealth - AbstractDungeon.player.currentHealth) + 2;
			this.baseMagicNumber = this.magicNumber = MAGIC_NUM -1;
		}
		this.rawDescription = DESCRIPTION;
		loadCardImage("img/cards/Kuuga_TitanSword.png");
        initializeDescription();
	}

	@Override
	public void optionKiva() {
		this.name = NAME;
		this.baseDamage = ATTACK_DMG + (AbstractDungeon.player.maxHealth - AbstractDungeon.player.currentHealth);
		this.baseMagicNumber = this.magicNumber = MAGIC_NUM;
		if(upgraded) {
			this.baseDamage = ATTACK_DMG + (AbstractDungeon.player.maxHealth - AbstractDungeon.player.currentHealth) + 2;
			this.baseMagicNumber = this.magicNumber = MAGIC_NUM -1;
		}
		this.rawDescription = DESCRIPTION;
		loadCardImage("img/cards/Kuuga_TitanSword.png");
        initializeDescription();
	}

	@Override
	public void optionNeutral() {
		this.name = NAME;
		this.baseDamage = ATTACK_DMG + (AbstractDungeon.player.maxHealth - AbstractDungeon.player.currentHealth);
		this.baseMagicNumber = this.magicNumber = MAGIC_NUM;
		if(upgraded) {
			this.baseDamage = ATTACK_DMG + (AbstractDungeon.player.maxHealth - AbstractDungeon.player.currentHealth) + 2;
			this.baseMagicNumber = this.magicNumber = MAGIC_NUM -1;
		}
		this.rawDescription = DESCRIPTION;
		loadCardImage("img/cards/Kuuga_TitanSword.png");
        initializeDescription();
	}
    
	@Override
    public void upgrade() {
        if (!this.upgraded) {
            this.upgradeName();
            this.upgradeDamage(2);
            this.upgradeMagicNumber(-1);
        }
	
    }
    
    static {
    	cardStrings = CardCrawlGame.languagePack.getCardStrings("Kuuga_TitanSword");
        NAME = Kuuga_TitanSword.cardStrings.NAME;
        DESCRIPTION = Kuuga_TitanSword.cardStrings.DESCRIPTION;
        EXTENDED_DESCRIPTION = Kuuga_TitanSword.cardStrings.EXTENDED_DESCRIPTION;
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
