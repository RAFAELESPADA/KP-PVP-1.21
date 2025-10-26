/*     */ package me.RafaelAulerDeMeloAraujo.SpecialAbility;
/*     */ 
/*     */ import java.util.ArrayList;

/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.Sound;
/*     */ import org.bukkit.command.Command;
/*     */ import org.bukkit.command.CommandExecutor;
/*     */ import org.bukkit.command.CommandSender;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.event.EventHandler;
/*     */ import org.bukkit.event.Listener;
/*     */ import org.bukkit.event.inventory.InventoryClickEvent;
/*     */ import org.bukkit.inventory.Inventory;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ import org.bukkit.inventory.meta.ItemMeta;

import me.RafaelAulerDeMeloAraujo.Listeners.CustomHolder;
/*     */ import me.RafaelAulerDeMeloAraujo.main.Main;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class NewKitMenu
/*     */   implements Listener, CommandExecutor
/*     */ {
/*     */   private Main main;
/*     */   static Main plugin;
/*     */   
/*     */   public NewKitMenu(Main main)
/*     */   {
/*  31 */     this.main = main;
/*  32 */     plugin = main;
/*     */   }
/*     */   
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public static void OPENKIT(Player p) {
	Inventory kits = Bukkit.createInventory(new CustomHolder(), 54, Main.messages.getString("KitsInventoryName").replace("&", "§"));
	/*     */    
kits.clear();
	/* 222 */       ItemStack vidro1 = new ItemStack(Material.valueOf(Main.getInstace().getConfig().getString("KITMENU-BACKGROUND-ITEM-MATERIAL")));
	/* 223 */       ItemMeta vidro12 = vidro1.getItemMeta();
	/* 224 */       vidro12.setDisplayName("§a§k========");
	/* 225 */       vidro1.setItemMeta(vidro12);
	/*     */       
	/* 227 */      ItemStack barrier = new ItemStack(Material.BARRIER);
	/* 228 */       ItemMeta barrier2 = barrier.getItemMeta();
	/* 229 */       barrier2.setDisplayName((Main.messages.getString("CloseKitMenuBottom").replace("&", "§")));
	/* 230 */       barrier.setItemMeta(barrier2);
	/*     */       
	/*     */ 
	/* 233 */       
	/* 234 */       
	/* 252 */       kits.setItem(46, vidro1);
	/* 253 */       kits.setItem(47, vidro1);
	/* 254 */       kits.setItem(48, vidro1);
	/* 255 */       kits.setItem(49, vidro1);
	/* 256 */       kits.setItem(50, vidro1);
	/* 257 */       kits.setItem(51, vidro1);
	/* 258 */       kits.setItem(52, vidro1);
	/* 259 */       kits.setItem(53, barrier);

	/* 272 */        {
	/* 273 */         ItemStack pyro = new ItemStack(Material.WOODEN_SWORD);
	/* 274 */         ItemMeta metapyro = pyro.getItemMeta();
	/* 275 */         metapyro.setDisplayName("§7Kit §e§lBasic");
	/* 276 */         ArrayList indiob = new ArrayList();
	/* 277 */         indiob.add(Main.kits.getString("BasicLore").replace("&", "§"));
	/* 278 */         metapyro.setLore(indiob);
	/* 279 */         pyro.setItemMeta(metapyro);
	/* 280 */         kits.addItem(new ItemStack[] { pyro });
	/*     */       }
	/* 272 */       if (p.hasPermission("kitpvp.kit.pvp")) {
	/* 273 */         ItemStack pyro = new ItemStack(Material.DIAMOND_SWORD);
	/* 274 */         ItemMeta metapyro = pyro.getItemMeta();
	/* 275 */         metapyro.setDisplayName("§7Kit §e§lPvP");
	/* 276 */         ArrayList indiob = new ArrayList();
	/* 277 */        indiob.add(Main.kits.getString("PvPLore").replace("&", "§"));
	/* 278 */         metapyro.setLore(indiob);
	/* 279 */         pyro.setItemMeta(metapyro);
	/* 280 */         kits.addItem(new ItemStack[] { pyro });
	/*     */       }

if (p.hasPermission("kitpvp.kit.berserker")) {
/* 555 */         ItemStack pyro = new ItemStack(Material.GOLDEN_CARROT);
/* 556 */         ItemMeta metapyro = pyro.getItemMeta();
/* 557 */         metapyro.setDisplayName("§7Kit §e§lBerserker");
/* 558 */         ArrayList indiob = new ArrayList();
/* 559 */         indiob.add((Main.kits.getString("BerserkerLore")).replace("&", "§"));
/* 561 */         metapyro.setLore(indiob);
/* 562 */         pyro.setItemMeta(metapyro);
	/* 280 */     kits.addItem(new ItemStack[] { pyro });      
/*     */        }
	/* 282 */       if (p.hasPermission("kitpvp.kit.kangaroo")) {
	/* 283 */         ItemStack pyro = new ItemStack(Material.FIREWORK_ROCKET);
	/* 284 */         ItemMeta metapyro = pyro.getItemMeta();
	/* 285 */         metapyro.setDisplayName("§7Kit §e§lKangaroo");
	/* 286 */         ArrayList indiob = new ArrayList();
	/* 287 */         indiob.add(API.cor(Main.kits.getString("KangarooLore")));
	/* 289 */         metapyro.setLore(indiob);
	/* 290 */         pyro.setItemMeta(metapyro);
	/* 291 */         kits.addItem(new ItemStack[] { pyro });
	/*     */       }
	/* 293 */       if (p.hasPermission("kitpvp.kit.archer")) {
	/* 294 */         ItemStack pyro = new ItemStack(Material.BOW);
	/* 295 */         ItemMeta metapyro = pyro.getItemMeta();
	/* 296 */         metapyro.setDisplayName("§7Kit §e§lArcher");
	/* 297 */         ArrayList indiob = new ArrayList();
	/* 298 */         indiob.add(Main.kits.getString("ArcherLore").replace("&", "§"));
	/* 299 */         metapyro.setLore(indiob);
	/* 300 */         pyro.setItemMeta(metapyro);
	/* 301 */         kits.addItem(new ItemStack[] { pyro });
	/*     */       }
	/* 303 */       if (p.hasPermission("kitpvp.kit.tank")) {
	/* 304 */         ItemStack pyro = new ItemStack(Material.DIAMOND_CHESTPLATE);
	/*     */         
	/* 306 */         ItemMeta metapyro = pyro.getItemMeta();
	/* 307 */         metapyro.setDisplayName("§7Kit §e§lTank");
	/* 308 */         ArrayList indiob = new ArrayList();
	/* 309 */         indiob.add(Main.kits.getString("TankLore").replace("&", "§"));
	/* 310 */         
	/* 311 */         metapyro.setLore(indiob);
	/* 312 */         pyro.setItemMeta(metapyro);
	/* 313 */         kits.addItem(new ItemStack[] { pyro });
	/*     */       }
	/* 303 */       if (p.hasPermission("kitpvp.kit.hedgehog")) {
	/* 304 */         ItemStack pyro = new ItemStack(Material.ARROW);
	/*     */         
	/* 306 */         ItemMeta metapyro = pyro.getItemMeta();
	/* 307 */         metapyro.setDisplayName("§7Kit §e§lHedgeHog");
	/* 308 */         ArrayList indiob = new ArrayList();
	/* 309 */         indiob.add(Main.kits.getString("HedgeHogLore").replace("&", "§"));
	/* 310 */         
	/* 311 */         metapyro.setLore(indiob);
	/* 312 */         pyro.setItemMeta(metapyro);
	/* 313 */         kits.addItem(new ItemStack[] { pyro });
	/*     */       }
	/* 315 */       if (p.hasPermission("kitpvp.kit.snail")) {
	/* 316 */         ItemStack pyro = new ItemStack(Material.FERMENTED_SPIDER_EYE);
	/* 317 */         ItemMeta metapyro = pyro.getItemMeta();
	/* 318 */         metapyro.setDisplayName("§7Kit §e§lSnail");
	/* 319 */         ArrayList indiob = new ArrayList();
	/* 320 */         indiob.add((Main.kits.getString("SnailLore").replace("&", "§")));
	/* 321 */         metapyro.setLore(indiob);
	/* 322 */         pyro.setItemMeta(metapyro);
	/* 323 */         kits.addItem(new ItemStack[] { pyro });
	/*     */       }
	/* 325 */       if (p.hasPermission("kitpvp.kit.viper")) {
	/* 326 */         ItemStack pyro = new ItemStack(Material.SPIDER_EYE);
	/* 327 */         ItemMeta metapyro = pyro.getItemMeta();
	/* 328 */         metapyro.setDisplayName("§7Kit §e§lViper");
	/* 329 */         ArrayList indiob = new ArrayList();
	/* 330 */         indiob.add((Main.kits.getString("ViperLore").replace("&", "§")));
	/* 331 */         metapyro.setLore(indiob);
	/* 332 */         pyro.setItemMeta(metapyro);
	/* 333 */         kits.addItem(new ItemStack[] { pyro });
	/*     */       }
	/* 335 */       if (p.hasPermission("kitpvp.kit.stomper")) {
	/* 336 */         ItemStack pyro = new ItemStack(Material.IRON_BOOTS);
	/* 337 */         ItemMeta metapyro = pyro.getItemMeta();
	/* 338 */         metapyro.setDisplayName("§7Kit §e§lStomper");
	/* 339 */         ArrayList indiob = new ArrayList();
	/* 341 */         indiob.add((Main.kits.getString("StomperLore").replace("&", "§")));
	/* 342 */         metapyro.setLore(indiob);
	/* 343 */         pyro.setItemMeta(metapyro);
	/* 344 */         kits.addItem(new ItemStack[] { pyro });
	/*     */       }
	/* 346 */       if (p.hasPermission("kitpvp.kit.deshfire")) {
	/* 347 */         ItemStack pyro = new ItemStack(Material.REDSTONE_BLOCK);
	/* 348 */         ItemMeta metapyro = pyro.getItemMeta();
	/* 349 */         metapyro.setDisplayName("§7Kit §e§lDeshfire");
	/* 350 */         ArrayList indiob = new ArrayList();
	/* 351 */         indiob.add((Main.kits.getString("DeshfireLore").replace("&", "§")));
	/* 352 */         
	/* 353 */         metapyro.setLore(indiob);
	/* 354 */         pyro.setItemMeta(metapyro);
	/* 355 */         kits.addItem(new ItemStack[] { pyro });
	/*     */       }
	/* 368 */       if (p.hasPermission("kitpvp.kit.critical")) {
	/* 369 */         ItemStack pyro = new ItemStack(Material.GOLDEN_APPLE);
	/* 370 */         ItemMeta metapyro = pyro.getItemMeta();
	/* 371 */         metapyro.setDisplayName("§7Kit §e§lCritical");
	/* 372 */         ArrayList indiob = new ArrayList();
	/* 373 */         indiob.add((Main.kits.getString("CriticalLore").replace("&", "§")));
	/* 374 */         metapyro.setLore(indiob);
	/* 375 */         pyro.setItemMeta(metapyro);
	/* 376 */         kits.addItem(new ItemStack[] { pyro });
	/*     */       }
	/* 378 */       if (p.hasPermission("kitpvp.kit.gladiator")) {
	/* 379 */         ItemStack pyro = new ItemStack(Material.IRON_BARS);
	/* 380 */         ItemMeta metapyro = pyro.getItemMeta();
	/* 381 */         metapyro.setDisplayName("§7Kit §e§lGladiator");
	/* 382 */         ArrayList indiob = new ArrayList();
	/* 383 */         indiob.add((Main.kits.getString("GladiatorLore").replace("&", "§")));
	/* 384 */         metapyro.setLore(indiob);
	/* 385 */         pyro.setItemMeta(metapyro);
	/* 386 */         kits.addItem(new ItemStack[] { pyro });
	/*     */       }
	/* 388 */       if (p.hasPermission("kitpvp.kit.thor")) {
	/* 389 */         ItemStack pyro = new ItemStack(Material.GOLDEN_AXE);
	/* 390 */         ItemMeta metapyro = pyro.getItemMeta();
	/* 391 */         metapyro.setDisplayName("§7Kit §e§lThor");
	/* 392 */         ArrayList indiob = new ArrayList();
	/* 393 */         indiob.add(Main.kits.getString("ThorLore").replace("&", "§"));
	/* 395 */         metapyro.setLore(indiob);
	/* 396 */         pyro.setItemMeta(metapyro);
	/* 397 */         kits.addItem(new ItemStack[] { pyro });
	/*     */       }
	if (p.hasPermission("kitpvp.kit.tamer"))
	/*     */       {
	/* 555 */         ItemStack pyro = new ItemStack(Material.BONE);
	/* 556 */         ItemMeta metapyro = pyro.getItemMeta();
	/* 557 */         metapyro.setDisplayName("§7Kit §e§lTamer");
	/* 558 */         ArrayList indiob = new ArrayList();
	/* 559 */         indiob.add((Main.kits.getString("TamerLore")).replace("&", "§"));
	/* 560 */         
	/* 561 */         metapyro.setLore(indiob);
	/* 562 */         pyro.setItemMeta(metapyro);
	/* 563 */         kits.addItem(new ItemStack[] { pyro });
	/*     */       }
	/* 399 */       if (p.hasPermission("kitpvp.kit.doublejump")) {
	/* 400 */         ItemStack pyro = new ItemStack(Material.CHAINMAIL_BOOTS);
	/* 401 */         ItemMeta metapyro = pyro.getItemMeta();
	/* 402 */         metapyro.setDisplayName("§7Kit §e§lDoubleJump");
	/* 403 */         ArrayList indiob = new ArrayList();
	/* 404 */      indiob.add(Main.kits.getString("DoubleJumpLore").replace("&", "§"));
	/* 406 */         metapyro.setLore(indiob);
	/* 407 */         pyro.setItemMeta(metapyro);
	/* 408 */         kits.addItem(new ItemStack[] { pyro });
	/*     */       }
	/* 410 */       if (p.hasPermission("kitpvp.kit.bomber")) {
	/* 411 */         ItemStack pyro = new ItemStack(Material.TNT);
	/* 412 */         ItemMeta metapyro = pyro.getItemMeta();
	/* 413 */         metapyro.setDisplayName("§7Kit §e§lBomber");
	/* 414 */         ArrayList indiob = new ArrayList();
	/* 415 */         indiob.add(Main.kits.getString("BomberLore").replace("&", "§"));
	/* 417 */         metapyro.setLore(indiob);
	/* 418 */         pyro.setItemMeta(metapyro);
	/* 419 */         kits.addItem(new ItemStack[] { pyro });
	/*     */       }
	/* 421 */       if (p.hasPermission("kitpvp.kit.wasp")) {
	/* 422 */         ItemStack pyro = new ItemStack(Material.BLAZE_ROD);
	/* 423 */         ItemMeta metapyro = pyro.getItemMeta();
	/* 424 */         metapyro.setDisplayName("§7Kit §e§lWasp");
	/* 425 */         ArrayList indiob = new ArrayList();
	/* 426 */        indiob.add(Main.kits.getString("WaspLore").replace("&", "§"));
	/* 428 */         metapyro.setLore(indiob);
	/* 429 */         pyro.setItemMeta(metapyro);
	/* 430 */         kits.addItem(new ItemStack[] { pyro });
	/*     */       }
	/*     */       
	/* 421 */       if (p.hasPermission("kitpvp.kit.confuser")) {
	/* 422 */         ItemStack pyro = new ItemStack(Material.POTION);
	/* 423 */         ItemMeta metapyro = pyro.getItemMeta();
	/* 424 */         metapyro.setDisplayName("§7Kit §e§lConfuser");
	/* 425 */         ArrayList indiob = new ArrayList();
	/* 426 */        indiob.add(Main.kits.getString("ConfuserLore").replace("&", "§"));
	/* 428 */         metapyro.setLore(indiob);
	/* 429 */         pyro.setItemMeta(metapyro);
	/* 430 */         kits.addItem(new ItemStack[] { pyro });
	/*     */       }
	/* 432 */       if (p.hasPermission("kitpvp.kit.airman")) {
	/* 433 */         ItemStack pyro = new ItemStack(Material.FEATHER);
	/* 434 */         ItemMeta metapyro = pyro.getItemMeta();
	/* 435 */         metapyro.setDisplayName("§7Kit §e§lAirman");
	/* 436 */         ArrayList indiob = new ArrayList();
	/* 437 */         indiob.add(Main.kits.getString("AirmanLore").replace("&", "§"));
	/* 439 */         metapyro.setLore(indiob);
	/* 440 */         pyro.setItemMeta(metapyro);
	/* 441 */         kits.addItem(new ItemStack[] { pyro });
	/*     */       }
	/* 443 */       if (p.hasPermission("kitpvp.kit.switcher"))
	/*     */       {
	/* 445 */         ItemStack pyro = new ItemStack(Material.SNOWBALL, 16);
	/* 446 */         ItemMeta metapyro = pyro.getItemMeta();
	/* 447 */         metapyro.setDisplayName("§7Kit §e§lSwitcher");
	/* 448 */         ArrayList indiob = new ArrayList();
	/* 449 */         indiob.add(Main.kits.getString("SwitcherLore").replace("&", "§"));
	/* 451 */         metapyro.setLore(indiob);
	/* 452 */         pyro.setItemMeta(metapyro);
	/* 453 */         kits.addItem(new ItemStack[] { pyro });
	/*     */       }
	/* 455 */       if (p.hasPermission("kitpvp.kit.ninja")) {
	/* 456 */         ItemStack pyro = new ItemStack(Material.COAL);
	/* 457 */         ItemMeta metapyro = pyro.getItemMeta();
	/* 458 */         metapyro.setDisplayName("§7Kit §e§lNinja");
	/* 459 */         ArrayList indiob = new ArrayList();
	/* 460 */         metapyro.setLore(indiob);
	/* 461 */         indiob.add(Main.kits.getString("NinjaLore").replace("&", "§"));
	/* 464 */         metapyro.setLore(indiob);
	/* 465 */         pyro.setItemMeta(metapyro);
	/* 466 */         kits.addItem(new ItemStack[] { pyro });
	/*     */       }
	/* 468 */       if (p.hasPermission("kitpvp.kit.cactus")) {
	/* 469 */         ItemStack pyro = new ItemStack(Material.CACTUS);
	/* 470 */         ItemMeta metapyro = pyro.getItemMeta();
	/* 471 */         metapyro.setDisplayName("§7Kit §e§lCactus");
	/* 472 */         ArrayList indiob = new ArrayList();
	/* 473 */         indiob.add(Main.kits.getString("CactusLore").replace("&", "§"));
	/*     */         
	/* 475 */         metapyro.setLore(indiob);
	/* 476 */         pyro.setItemMeta(metapyro);
	/* 477 */         kits.addItem(new ItemStack[] { pyro });
	/*     */       }
	if (p.hasPermission("kitpvp.kit.antistomper")) {
	/* 469 */         ItemStack pyro = new ItemStack(Material.NETHERITE_HELMET);
	/* 470 */         ItemMeta metapyro = pyro.getItemMeta();
	/* 471 */         metapyro.setDisplayName("§7Kit §e§lAntiStomper");
	/* 472 */         ArrayList indiob = new ArrayList();
	/* 473 */         indiob.add(Main.kits.getString("AntiStomperLore").replace("&", "§"));
	/*     */         
	/* 475 */         metapyro.setLore(indiob);
	/* 476 */         pyro.setItemMeta(metapyro);
	/* 477 */         kits.addItem(new ItemStack[] { pyro });
	/*     */       }
	/* 479 */       if (p.hasPermission("kitpvp.kit.pyro")) {
	/* 480 */         ItemStack pyro = new ItemStack(Material.FLINT_AND_STEEL);
	/* 481 */         ItemMeta metapyro = pyro.getItemMeta();
	/* 482 */         metapyro.setDisplayName("§7Kit §e§lPyro");
	/* 483 */         ArrayList indiob = new ArrayList();
	/* 484 */         indiob.add(Main.kits.getString("PyroLore").replace("&", "§"));
	/*     */         
	/* 486 */         metapyro.setLore(indiob);
	/* 487 */         pyro.setItemMeta(metapyro);
	/* 488 */         kits.addItem(new ItemStack[] { pyro });
	/*     */       }
	if (p.hasPermission("kitpvp.kit.creeper")) {
		/* 283 */         ItemStack pyro = new ItemStack(Material.COMPARATOR);
		/* 284 */         ItemMeta metapyro = pyro.getItemMeta();
		/* 285 */         metapyro.setDisplayName("§7Kit §e§lCreeper");
		/* 286 */         ArrayList indiob = new ArrayList();
		/* 287 */         indiob.add(API.cor(Main.kits.getString("CreeperLore")));
		/* 289 */         metapyro.setLore(indiob);
		/* 290 */         pyro.setItemMeta(metapyro);
			/* 280 */     kits.addItem(new ItemStack[] { pyro });
			/*     */       /*     */       }
	/* 490 */       if (p.hasPermission("kitpvp.kit.jumper")) {
	/* 491 */         ItemStack pyro = new ItemStack(Material.LEATHER_BOOTS);
	/* 492 */         ItemMeta metapyro = pyro.getItemMeta();
	/* 493 */         metapyro.setDisplayName("§7Kit §e§lJumper");
	/* 494 */         ArrayList indiob = new ArrayList();
	/* 495 */         indiob.add(Main.kits.getString("JumperLore").replace("&", "§"));
	/*     */         
	/* 497 */         metapyro.setLore(indiob);
	/* 498 */         pyro.setItemMeta(metapyro);
	/* 499 */         kits.addItem(new ItemStack[] { pyro });
	/*     */       }
	/*     */       
	/* 502 */       if (p.hasPermission("kitpvp.kit.timelord"))
	/*     */       {
	/* 504 */         ItemStack pyro = new ItemStack(Material.CLOCK);
	/* 505 */         ItemMeta metapyro = pyro.getItemMeta();
	/* 506 */         metapyro.setDisplayName("§7Kit §e§lTimeLord");
	/* 507 */         ArrayList indiob = new ArrayList();
	/* 508 */         indiob.add(Main.kits.getString("TimelordLore").replace("&", "§"));
	/* 511 */         metapyro.setLore(indiob);
	/* 512 */         pyro.setItemMeta(metapyro);
	/* 513 */         kits.addItem(new ItemStack[] { pyro });
	/*     */       }
	/* 515 */       if (p.hasPermission("kitpvp.kit.fisherman"))
	/*     */       {
	/* 517 */         ItemStack pyro = new ItemStack(Material.FISHING_ROD);
	/* 518 */         ItemMeta metapyro = pyro.getItemMeta();
	/* 519 */         metapyro.setDisplayName("§7Kit §e§lFisherman");
	/* 520 */         ArrayList indiob = new ArrayList();
	/* 521 */         indiob.add(Main.kits.getString("FishermanLore").replace("&", "§"));
	/* 523 */         metapyro.setLore(indiob);
	/* 524 */         pyro.setItemMeta(metapyro);
	/* 525 */         kits.addItem(new ItemStack[] { pyro });
	/*     */       }
	/* 527 */       if (p.hasPermission("kitpvp.kit.spiderman"))
	/*     */       {
	/* 529 */         ItemStack pyro = new ItemStack(Material.COBWEB);
	/* 530 */         ItemMeta metapyro = pyro.getItemMeta();
	/* 531 */         metapyro.setDisplayName("§7Kit §e§lSpiderman");
	/* 532 */         ArrayList indiob = new ArrayList();
	/* 533 */         indiob.add(Main.kits.getString("SpidermanLore").replace("&", "§"));
	/* 536 */         metapyro.setLore(indiob);
	/* 537 */         pyro.setItemMeta(metapyro);
	/* 538 */         kits.addItem(new ItemStack[] { pyro });
	/*     */       }
	/* 540 */       if (p.hasPermission("kitpvp.kit.warper"))
	/*     */       {
	/* 542 */         ItemStack pyro = new ItemStack(Material.ENDER_PEARL);
	/* 543 */         ItemMeta metapyro = pyro.getItemMeta();
	/* 544 */         metapyro.setDisplayName("§7Kit §e§lWarper");
	/* 545 */         ArrayList indiob = new ArrayList();
	/* 546 */         indiob.add(Main.kits.getString("WarperLore").replace("&", "§"));
	/*     */         
	/* 549 */         metapyro.setLore(indiob);
	/* 550 */         pyro.setItemMeta(metapyro);
	/* 551 */         kits.addItem(new ItemStack[] { pyro });
	/*     */       }
	/* 540 */       if (p.hasPermission("kitpvp.kit.vampire"))
	/*     */       {
	/* 542 */         ItemStack pyro = new ItemStack(Material.GOLD_NUGGET);
	/* 543 */         ItemMeta metapyro = pyro.getItemMeta();
	/* 544 */         metapyro.setDisplayName("§7Kit §e§lVampire");
	/* 545 */         ArrayList indiob = new ArrayList();
	/* 546 */         indiob.add(Main.kits.getString("VampireLore").replace("&", "§"));
	/*     */         
	/* 549 */         metapyro.setLore(indiob);
	/* 550 */         pyro.setItemMeta(metapyro);
	/* 551 */         kits.addItem(new ItemStack[] { pyro });
	/*     */       }
	/* 553 */       if (p.hasPermission("kitpvp.kit.naruto"))
	/*     */       {
	/* 555 */         ItemStack pyro = new ItemStack(Material.NETHER_STAR);
	/* 556 */         ItemMeta metapyro = pyro.getItemMeta();
	/* 557 */         metapyro.setDisplayName("§7Kit §e§lNaruto");
	/* 558 */         ArrayList indiob = new ArrayList();
	/* 559 */         indiob.add(Main.kits.getString("NarutoLore").replace("&", "§"));
	/* 561 */         metapyro.setLore(indiob);
	/* 562 */         pyro.setItemMeta(metapyro);
	/* 563 */         kits.addItem(new ItemStack[] { pyro });
	/*     */       }
	/* 553 */       if (p.hasPermission("kitpvp.kit.sonic"))
	/*     */       {
	/* 555 */         ItemStack pyro = new ItemStack(Material.LAPIS_BLOCK);
	/* 556 */         ItemMeta metapyro = pyro.getItemMeta();
	/* 557 */         metapyro.setDisplayName("§7Kit §e§lSonic");
	/* 558 */         ArrayList indiob = new ArrayList();
	/* 559 */         indiob.add((Main.kits.getString("SonicLore")).replace("&", "§"));
	/* 560 */         
	/* 561 */         metapyro.setLore(indiob);
	/* 562 */         pyro.setItemMeta(metapyro);
	/* 563 */         kits.addItem(new ItemStack[] { pyro });
	/*     */       }
	/* 553 */       if (p.hasPermission("kitpvp.kit.phantom"))
	/*     */       {
	/* 555 */         ItemStack pyro = new ItemStack(Material.BOOK);
	/* 556 */         ItemMeta metapyro = pyro.getItemMeta();
	/* 557 */         metapyro.setDisplayName("§7Kit §e§lPhantom");
	/* 558 */         ArrayList indiob = new ArrayList();
	/* 559 */         indiob.add((Main.kits.getString("PhantomLore")).replace("&", "§"));
	/* 560 */         
	/* 561 */         metapyro.setLore(indiob);
	/* 562 */         pyro.setItemMeta(metapyro);
	/* 563 */         kits.addItem(new ItemStack[] { pyro });
	/*     */       }
	/* 553 */       if (p.hasPermission("kitpvp.kit.camel"))
	/*     */       {
	/* 555 */         ItemStack pyro = new ItemStack(Material.SAND);
	/* 556 */         ItemMeta metapyro = pyro.getItemMeta();
	/* 557 */         metapyro.setDisplayName("§7Kit §e§lCamel");
	/* 558 */         ArrayList indiob = new ArrayList();
	/* 559 */         indiob.add((Main.kits.getString("CamelLore")).replace("&", "§"));
	/* 560 */         
	/* 561 */         metapyro.setLore(indiob);
	/* 562 */         pyro.setItemMeta(metapyro);
	/* 563 */         kits.addItem(new ItemStack[] { pyro });
	/*     */       }
	/* 553 */       if (p.hasPermission("kitpvp.kit.viking"))
	/*     */       {
	/* 555 */         ItemStack pyro = new ItemStack(Material.IRON_AXE);
	/* 556 */         ItemMeta metapyro = pyro.getItemMeta();
	/* 557 */         metapyro.setDisplayName("§7Kit §e§lViking");
	/* 558 */         ArrayList indiob = new ArrayList();
	/* 559 */         indiob.add((Main.kits.getString("VikingLore")).replace("&", "§"));
	/* 560 */         
	/* 561 */         metapyro.setLore(indiob);
	/* 562 */         pyro.setItemMeta(metapyro);
	/* 563 */         kits.addItem(new ItemStack[] { pyro });
	/*     */       }
	/* 553 */       if (p.hasPermission("kitpvp.kit.poseidon"))
	/*     */       {
	/* 555 */         ItemStack pyro = new ItemStack(Material.WATER_BUCKET);
	/* 556 */         ItemMeta metapyro = pyro.getItemMeta();
	/* 557 */         metapyro.setDisplayName("§7Kit §e§lPoseidon");
	/* 558 */         ArrayList indiob = new ArrayList();
	/* 559 */         indiob.add((Main.kits.getString("PoseidonLore")).replace("&", "§"));
	/* 560 */         
	/* 561 */         metapyro.setLore(indiob);
	/* 562 */         pyro.setItemMeta(metapyro);
	/* 563 */         kits.addItem(new ItemStack[] { pyro });
	/*     */       }
	if (p.hasPermission("kitpvp.kit.milkman"))
	/*     */       {
	/* 555 */         ItemStack pyro = new ItemStack(Material.MILK_BUCKET);
	/* 556 */         ItemMeta metapyro = pyro.getItemMeta();
	/* 557 */         metapyro.setDisplayName("§7Kit §e§lMilkMan");
	/* 558 */         ArrayList indiob = new ArrayList();
	/* 559 */         indiob.add((Main.kits.getString("MilkmanLore")).replace("&", "§"));
	/* 560 */         
	/* 561 */         metapyro.setLore(indiob);
	/* 562 */         pyro.setItemMeta(metapyro);
	/* 563 */         kits.addItem(new ItemStack[] { pyro });
	/*     */       }
	/* 553 */       if (p.hasPermission("kitpvp.kit.anchor"))
	/*     */       {
	/* 555 */         ItemStack pyro = new ItemStack(Material.ANVIL);
	/* 556 */         ItemMeta metapyro = pyro.getItemMeta();
	/* 557 */         metapyro.setDisplayName("§7Kit §e§lAnchor");
	/* 558 */         ArrayList indiob = new ArrayList();
	/* 559 */         indiob.add((Main.kits.getString("AnchorLore")).replace("&", "§"));
	/* 560 */         
	/* 561 */         metapyro.setLore(indiob);
	/* 562 */         pyro.setItemMeta(metapyro);
	/* 563 */         kits.addItem(new ItemStack[] { pyro });
	/*     */       }
	/* 553 */       if (p.hasPermission("kitpvp.kit.resouper"))
	/*     */       {
	/* 555 */         ItemStack pyro = new ItemStack(Material.MUSHROOM_STEW);
	/* 556 */         ItemMeta metapyro = pyro.getItemMeta();
	/* 557 */         metapyro.setDisplayName("§7Kit §e§lResouper");
	/* 558 */         ArrayList indiob = new ArrayList();
	/* 559 */         indiob.add((Main.kits.getString("ResouperLore")).replace("&", "§"));
	/* 560 */         
	/* 561 */         metapyro.setLore(indiob);
	/* 562 */         pyro.setItemMeta(metapyro);
	/* 563 */         kits.addItem(new ItemStack[] { pyro });
	/*     */       }
	      if (p.hasPermission("kitpvp.kit.monk"))
	/*     */       {
	/* 555 */         ItemStack pyro = new ItemStack(Material.BLAZE_ROD);
	/* 556 */         ItemMeta metapyro = pyro.getItemMeta();
	/* 557 */         metapyro.setDisplayName("§7Kit §e§lMonk");
	/* 558 */         ArrayList indiob = new ArrayList();
	/* 559 */         indiob.add((Main.kits.getString("MonkLore")).replace("&", "§"));
	/* 560 */         
	/* 561 */         metapyro.setLore(indiob);
	/* 562 */         pyro.setItemMeta(metapyro);
	/* 563 */         kits.addItem(new ItemStack[] { pyro });
	/*     */       }
	/* 553 */       if (p.hasPermission("kitpvp.kit.fireman"))
	/*     */       {
	/* 555 */         ItemStack pyro = new ItemStack(Material.LAVA_BUCKET);
	/* 556 */         ItemMeta metapyro = pyro.getItemMeta();
	/* 557 */         metapyro.setDisplayName("§7Kit §e§lFireman");
	/* 558 */         ArrayList indiob = new ArrayList();
	/* 559 */         indiob.add((Main.kits.getString("FiremanLore")).replace("&", "§"));
	/* 560 */         
	/* 561 */         metapyro.setLore(indiob);
	/* 562 */         pyro.setItemMeta(metapyro);
	/* 563 */         kits.addItem(new ItemStack[] { pyro });
	/*     */       }
	/* 553 */       if (p.hasPermission("kitpvp.kit.ryu"))
	/*     */       {
	/* 555 */         ItemStack pyro = new ItemStack(Material.BEACON);
	/* 556 */         ItemMeta metapyro = pyro.getItemMeta();
	/* 557 */         metapyro.setDisplayName("§7Kit §e§lRyu");
	/* 558 */         ArrayList indiob = new ArrayList();
	/* 559 */         indiob.add((Main.kits.getString("RyuLore")).replace("&", "§"));
	/* 560 */         
	/* 561 */         metapyro.setLore(indiob);
	/* 562 */         pyro.setItemMeta(metapyro);
	/* 563 */         kits.addItem(new ItemStack[] { pyro });
	/*     */       }
	if (p.hasPermission("kitpvp.kit.shielder"))
	/*     */       {
	/* 555 */         ItemStack pyro = new ItemStack(Material.SHIELD);
	/* 556 */         ItemMeta metapyro = pyro.getItemMeta();
	/* 557 */         metapyro.setDisplayName("§7Kit §e§lShielder");
	/* 558 */         ArrayList indiob = new ArrayList();
	/* 559 */         indiob.add((Main.kits.getString("ShielderLore")).replace("&", "§"));
	/* 560 */         
	/* 561 */         metapyro.setLore(indiob);
	/* 562 */         pyro.setItemMeta(metapyro);
	/* 563 */         kits.addItem(new ItemStack[] { pyro });
	/*     */       }

if (p.hasPermission("kitpvp.kit.ghast"))
/*     */       {
/* 555 */         ItemStack pyro = new ItemStack(Material.GHAST_TEAR);
/* 556 */         ItemMeta metapyro = pyro.getItemMeta();
/* 557 */         metapyro.setDisplayName("§7Kit §e§lGhast");
/* 558 */         ArrayList indiob = new ArrayList();
/* 559 */         indiob.add((Main.kits.getString("GhastLore")).replace("&", "§"));
/* 560 */         
/* 561 */         metapyro.setLore(indiob);
/* 562 */         pyro.setItemMeta(metapyro);
/* 563 */         kits.addItem(new ItemStack[] { pyro });
/*     */       
	/*     */       }
	/*     */       
	/* 575 */       p.openInventory(kits);
	/*     */     }


/*     */ public static void playSound(Player player, String soundName, float volume, float pitch) {
	   try {
	       
	        Class<?> clazz = null;
	       try {
	       clazz = Class.forName("org.bukkit.Registry");        
	       
	       }  catch (ClassNotFoundException | NoClassDefFoundError e) {
	           player.playSound(player.getLocation(), Sound.valueOf(soundName), volume, pitch);
	       }
	       if (clazz == null) {
	    	   clazz = Class.forName("org.bukkit.Registry");        
	       }
	           player.playSound(player.getLocation(), Sound.valueOf(soundName), volume, pitch); //
	       
	   } catch (IllegalArgumentException e) {
	       Bukkit.getLogger().warning("[KP-PVP] INVALID SOUND NAME PARSED!");
	       e.printStackTrace();
	   }
	   catch (NullPointerException e) {
	       Bukkit.getLogger().warning("[KP-PVP] INVALID SOUND NAME PARSED!!");
	       e.printStackTrace();
	   } catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block

	       Bukkit.getLogger().warning("[KP-PVP] INVALID SOUND NAME PARSED!!!");
	       e.printStackTrace();
	}
	   }
/*     */ 
/*     */   @EventHandler
/*     */   public void kits(InventoryClickEvent e)
/*     */   {
/*  48 */     if ((e.getCurrentItem() != null) && (e.getCurrentItem().getItemMeta() != null))
/*     */     {
/*  50 */      
/*  51 */       Player p = (Player)e.getWhoClicked();
/*  52 */       	if (e.getCurrentItem() == null) {
	return;
}
if (e.getClickedInventory() == null) {
	return;
}
if (!(e.getClickedInventory().getHolder() instanceof CustomHolder)) {
	return;
}
/*  54 */         playSound(p, this.main.getConfig().getString("Sound.KitMenu"), 1.0F, 1.0F);
/*     */         
/*  56 */         e.setCancelled(true);
/*     */         
/*     */ 
/*     */ 
/*  60 */         if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§7Kit §e§lPvP")) {
/*  61 */           Bukkit.dispatchCommand(p, "kpvp");
/*  62 */           p.closeInventory();
/*     */         }
/*     */         
/*  65 */         if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§7Kit §e§lArcher"))
/*     */         {
/*  67 */           Bukkit.dispatchCommand(p, "archer");
/*  68 */           p.closeInventory();
/*     */         }
if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§7Kit §e§lWarper"))
/*     */         {
/*  72 */           Bukkit.dispatchCommand(p, "warper");
/*  73 */           p.closeInventory();
/*     */         }

if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§7Kit §e§lCreeper"))
/*     */         {
/* 168 */           Bukkit.dispatchCommand(p, "kcreeper");
/* 169 */           p.closeInventory();
/*     */         }
if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§7Kit §e§lBerserker")) {
/*  61 */           Bukkit.dispatchCommand(p, "kberserker");
/*  62 */           p.closeInventory();
/*     */         }
/*  70 */         if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§7Kit §e§lGhast"))
/*     */         {
/*  72 */           Bukkit.dispatchCommand(p, "kghast");
/*  73 */           p.closeInventory();
/*     */         }
/*     */         
/*  76 */         if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§7Kit §e§lKangaroo"))
/*     */         {
/*  78 */           Bukkit.dispatchCommand(p, "kangaroo");
/*  79 */           p.closeInventory();
/*     */         }
if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§7Kit §e§lHedgeHog"))
/*     */         {
/*  78 */           Bukkit.dispatchCommand(p, "khedgehog");
/*  79 */           p.closeInventory();
/*     */         }
/*  81 */         if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§7Kit §e§lFisherman"))
/*     */         {
/*  83 */           Bukkit.dispatchCommand(p, "fisherman");
/*  84 */           p.closeInventory();
/*     */         }
/*  86 */         if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§7Kit §e§lGladiator"))
/*     */         {
/*  88 */           Bukkit.dispatchCommand(p, "gladiator");
/*  89 */           p.closeInventory();
/*     */         }
/*  86 */         if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§7Kit §e§lPhantom"))
/*     */         {
/*  88 */           Bukkit.dispatchCommand(p, "kphantom");
/*  89 */           p.closeInventory();
/*     */         }
/*  91 */         if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§7Kit §e§lCritical"))
/*     */         {
/*  93 */           Bukkit.dispatchCommand(p, "critical");
/*  94 */           p.closeInventory();
/*     */         }
/*  96 */         if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§7Kit §e§lMonk"))
/*     */         {
/*  98 */           Bukkit.dispatchCommand(p, "monk");
/*  99 */           p.closeInventory();
/*     */         }
/*  96 */         if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§7Kit §e§lRyu"))
/*     */         {
/*  98 */           Bukkit.dispatchCommand(p, "kryu");
/*  99 */           p.closeInventory();
/*     */         }
/* 101 */         if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§7Kit §e§lSnail"))
/*     */         {
/* 103 */           Bukkit.dispatchCommand(p, "snail");
/* 104 */           p.closeInventory();
/*     */         }
/* 106 */         if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§7Kit §e§lViper"))
/*     */         {
/* 108 */           Bukkit.dispatchCommand(p, "viper");
/* 109 */           p.closeInventory();
/*     */         }
/* 111 */         if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§7Kit §e§lThor"))
/*     */         {
/* 113 */           Bukkit.dispatchCommand(p, "kthor");
/* 114 */           p.closeInventory();
/*     */         }
/* 111 */         if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§7Kit §e§lAnchor"))
/*     */         {
/* 113 */           Bukkit.dispatchCommand(p, "kanchor");
/* 114 */           p.closeInventory();
/*     */         }
/* 116 */         if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§7Kit §e§lNinja"))
/*     */         {
/* 118 */           Bukkit.dispatchCommand(p, "ninja");
/* 119 */           p.closeInventory();
/*     */         }
/* 121 */         if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§7Kit §e§lStomper"))
/*     */         {
/* 123 */           Bukkit.dispatchCommand(p, "stomper");
/* 124 */           p.closeInventory();
/*     */         }
if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§7Kit §e§lAntiStomper"))
/*     */         {
/* 123 */           Bukkit.dispatchCommand(p, "antistomper");
/* 124 */           p.closeInventory();
/*     */         }
/* 126 */         if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§7Kit §e§lTimeLord"))
/*     */         {
/* 128 */           Bukkit.dispatchCommand(p, "timelord");
/* 129 */           p.closeInventory();
/*     */         }
/* 131 */         if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§7Kit §e§lDoubleJump"))
/*     */         {
/* 133 */           Bukkit.dispatchCommand(p, "doublejump");
/* 134 */           p.closeInventory();
/*     */         }
/* 136 */         if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§7Kit §e§lJumper"))
/*     */         {
/* 138 */           Bukkit.dispatchCommand(p, "jumper");
/* 139 */           p.closeInventory();
/*     */         }
/* 141 */         if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§7Kit §e§lCactus"))
/*     */         {
/* 143 */           Bukkit.dispatchCommand(p, "cactus");
/* 144 */           p.closeInventory();
/*     */         }
if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§7Kit §e§lShielder"))
/*     */         {
/* 143 */           Bukkit.dispatchCommand(p, "kshielder");
/* 144 */           p.closeInventory();
/*     */         }
/* 146 */         if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§7Kit §e§lNaruto"))
/*     */         {
/* 148 */           Bukkit.dispatchCommand(p, "naruto");
/* 149 */           p.closeInventory();
/*     */         }
/* 151 */         if (e.getCurrentItem().getType() == Material.BARRIER) {
/* 152 */           p.closeInventory();
/* 153 */           p.sendMessage(Main.messages.getString("KitMenuClosed").replace("&", "§"));
/*     */         }
/*     */         
/* 156 */         if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§7Kit §e§lBomber"))
/*     */         {
/* 158 */           Bukkit.dispatchCommand(p, "bomber");
/* 159 */           p.closeInventory();
/*     */         }
/* 161 */         if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§7Kit §e§lBasic"))
/*     */         {
/* 163 */           Bukkit.dispatchCommand(p, "basic");
/* 164 */           p.closeInventory();
/*     */         }
/* 166 */         if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§7Kit §e§lSpiderman"))
/*     */         {
/* 168 */           Bukkit.dispatchCommand(p, "spiderman");
/* 169 */           p.closeInventory();
/*     */         }
/* 171 */         if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§7Kit §e§lAirman"))
/*     */         {
/* 173 */           Bukkit.dispatchCommand(p, "airman");
/* 174 */           p.closeInventory();
/*     */         }
/* 171 */         if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§7Kit §e§lVampire"))
/*     */         {
/* 173 */           Bukkit.dispatchCommand(p, "kvampire");
/* 174 */           p.closeInventory();
/*     */         }
/* 176 */         if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§7Kit §e§lSwitcher"))
/*     */         {
/* 178 */           Bukkit.dispatchCommand(p, "switcher");
/* 179 */           p.closeInventory();
/*     */         }
/* 181 */         if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§7Kit §e§lWasp"))
/*     */         {
/* 183 */           Bukkit.dispatchCommand(p, "wasp");
/* 184 */           p.closeInventory();
/*     */         }
/* 186 */         if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§7Kit §e§lPyro"))
/*     */         {
/* 188 */           Bukkit.dispatchCommand(p, "pyro");
/* 189 */           p.closeInventory();
/*     */         }
/* 191 */         if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§7Kit §e§lTank"))
/*     */         {
/* 193 */           Bukkit.dispatchCommand(p, "tank");
/* 194 */           p.closeInventory();
/*     */         }
if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§7Kit §e§lMilkMan"))
/*     */         {
/* 193 */           Bukkit.dispatchCommand(p, "kmilkman");
/* 194 */           p.closeInventory();
/*     */         }
//
/* 191 */         if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§7Kit §e§lSonic"))
/*     */         {
/* 193 */           Bukkit.dispatchCommand(p, "sonic");
/* 194 */           p.closeInventory();
/*     */         }
/* 191 */         if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§7Kit §e§lCamel"))
/*     */         {
/* 193 */           Bukkit.dispatchCommand(p, "kcamel");
/* 194 */           p.closeInventory();
/*     */         }
/* 191 */         if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§7Kit §e§lViking"))
/*     */         {
/* 193 */           Bukkit.dispatchCommand(p, "kviking");
/* 194 */           p.closeInventory();
/*     */         }
/* 191 */         if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§7Kit §e§lPoseidon"))
/*     */         {
/* 193 */           Bukkit.dispatchCommand(p, "kposeidon");
/* 194 */           p.closeInventory();
/*     */         }
/* 191 */         if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§7Kit §e§lConfuser"))
/*     */         {
/* 193 */           Bukkit.dispatchCommand(p, "kconfuser");
/* 194 */           p.closeInventory();
/*     */         }
/* 191 */         if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§7Kit §e§lFireman"))
/*     */         {
/* 193 */           Bukkit.dispatchCommand(p, "kfireman");
/* 194 */           p.closeInventory();
/*     */         }
/* 191 */         if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§7Kit §e§lMonk"))
/*     */         {
/* 193 */           Bukkit.dispatchCommand(p, "kmonk");
/* 194 */           p.closeInventory();
/*     */         }
if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§7Kit §e§lTamer"))
/*     */         {
/* 193 */           Bukkit.dispatchCommand(p, "ktamer");
/* 194 */           p.closeInventory();
/*     */         }
/* 191 */         if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§7Kit §e§lResouper"))
/*     */         {
/* 193 */           Bukkit.dispatchCommand(p, "kresouper");
/* 194 */           p.closeInventory();
/*     */         }
/* 196 */         if (e.getCurrentItem().getItemMeta().getDisplayName().equals("§7Kit §e§lDeshfire"))
/*     */         {
/* 198 */           Bukkit.dispatchCommand(p, "deshfire");
/* 199 */           p.closeInventory();
/*     */         }
/*     */       }
/*     */     }
/*     */   
/*     */   
/*     */ 
/*     */ 
/*     */   public boolean onCommand(CommandSender sender, Command cmd, String CommandLabel, String[] args)
/*     */   {
/* 209 */     if (!(sender instanceof Player)) {
/* 210 */       return true;
/*     */     }
/* 212 */     Player p = (Player)sender;
/* 213 */     if (cmd.getName().equalsIgnoreCase("kpkitmenu"))
/*     */     {
/*     */       Inventory kits = Bukkit.createInventory(new CustomHolder(), 54, Main.messages.getString("KitsInventoryName").replace("&", "§"));
/* 222 */       ItemStack vidro1 = new ItemStack(Material.valueOf(Main.getInstace().getConfig().getString("KITMENU-BACKGROUND-ITEM-MATERIAL")));
/* 223 */       ItemMeta vidro12 = vidro1.getItemMeta();
/* 224 */       vidro12.setDisplayName("§a§k========");
/* 225 */       vidro1.setItemMeta(vidro12);
/*     */       
/* 227 */      ItemStack barrier = new ItemStack(Material.BARRIER);
/* 228 */       ItemMeta barrier2 = barrier.getItemMeta();
/* 229 */       barrier2.setDisplayName((Main.messages.getString("CloseKitMenuBottom").replace("&", "§")));
/* 230 */       barrier.setItemMeta(barrier2);
/*     */       
/*     */ 
/* 233 */       

/* 252 */       kits.setItem(46, vidro1);
/* 253 */       kits.setItem(47, vidro1);
/* 254 */       kits.setItem(48, vidro1);
/* 255 */       kits.setItem(49, vidro1);
/* 256 */       kits.setItem(50, vidro1);
/* 257 */       kits.setItem(51, vidro1);
/* 258 */       kits.setItem(52, vidro1);
/* 259 */       kits.setItem(53, barrier);

/* 272 */        {
/* 273 */         ItemStack pyro = new ItemStack(Material.WOODEN_SWORD);
/* 274 */         ItemMeta metapyro = pyro.getItemMeta();
/* 275 */         metapyro.setDisplayName("§7Kit §e§lBasic");
/* 276 */         ArrayList indiob = new ArrayList();
/* 277 */         indiob.add(Main.kits.getString("BasicLore").replace("&", "§"));
/* 278 */         metapyro.setLore(indiob);
/* 279 */         pyro.setItemMeta(metapyro);
/* 280 */         kits.addItem(new ItemStack[] { pyro });
/*     */       }
/* 272 */       if (p.hasPermission("kitpvp.kit.pvp")) {
/* 273 */         ItemStack pyro = new ItemStack(Material.DIAMOND_SWORD);
/* 274 */         ItemMeta metapyro = pyro.getItemMeta();
/* 275 */         metapyro.setDisplayName("§7Kit §e§lPvP");
/* 276 */         ArrayList indiob = new ArrayList();
/* 277 */        indiob.add(Main.kits.getString("PvPLore").replace("&", "§"));
/* 278 */         metapyro.setLore(indiob);
/* 279 */         pyro.setItemMeta(metapyro);
/* 280 */         kits.addItem(new ItemStack[] { pyro });
/*     */       }
/* 282 */       if (p.hasPermission("kitpvp.kit.kangaroo")) {
/* 283 */         ItemStack pyro = new ItemStack(Material.FIREWORK_ROCKET);
/* 284 */         ItemMeta metapyro = pyro.getItemMeta();
/* 285 */         metapyro.setDisplayName("§7Kit §e§lKangaroo");
/* 286 */         ArrayList indiob = new ArrayList();
/* 287 */         indiob.add(API.cor(Main.kits.getString("KangarooLore")));
/* 289 */         metapyro.setLore(indiob);
/* 290 */         pyro.setItemMeta(metapyro);
/* 291 */         kits.addItem(new ItemStack[] { pyro });
/*     */       }
/* 293 */       if (p.hasPermission("kitpvp.kit.archer")) {
/* 294 */         ItemStack pyro = new ItemStack(Material.BOW);
/* 295 */         ItemMeta metapyro = pyro.getItemMeta();
/* 296 */         metapyro.setDisplayName("§7Kit §e§lArcher");
/* 297 */         ArrayList indiob = new ArrayList();
/* 298 */         indiob.add(Main.kits.getString("ArcherLore").replace("&", "§"));
/* 299 */         metapyro.setLore(indiob);
/* 300 */         pyro.setItemMeta(metapyro);
/* 301 */         kits.addItem(new ItemStack[] { pyro });
/*     */       }
/* 303 */       if (p.hasPermission("kitpvp.kit.tank")) {
/* 304 */         ItemStack pyro = new ItemStack(Material.DIAMOND_CHESTPLATE);
/*     */         
/* 306 */         ItemMeta metapyro = pyro.getItemMeta();
/* 307 */         metapyro.setDisplayName("§7Kit §e§lTank");
/* 308 */         ArrayList indiob = new ArrayList();
/* 309 */         indiob.add(Main.kits.getString("TankLore").replace("&", "§"));
/* 310 */         
/* 311 */         metapyro.setLore(indiob);
/* 312 */         pyro.setItemMeta(metapyro);
/* 313 */         kits.addItem(new ItemStack[] { pyro });
/*     */       }
/* 315 */       if (p.hasPermission("kitpvp.kit.snail")) {
/* 316 */         ItemStack pyro = new ItemStack(Material.FERMENTED_SPIDER_EYE);
/* 317 */         ItemMeta metapyro = pyro.getItemMeta();
/* 318 */         metapyro.setDisplayName("§7Kit §e§lSnail");
/* 319 */         ArrayList indiob = new ArrayList();
/* 320 */         indiob.add((Main.kits.getString("SnailLore").replace("&", "§")));
/* 321 */         metapyro.setLore(indiob);
/* 322 */         pyro.setItemMeta(metapyro);
/* 323 */         kits.addItem(new ItemStack[] { pyro });
/*     */       }
/* 325 */       if (p.hasPermission("kitpvp.kit.viper")) {
/* 326 */         ItemStack pyro = new ItemStack(Material.SPIDER_EYE);
/* 327 */         ItemMeta metapyro = pyro.getItemMeta();
/* 328 */         metapyro.setDisplayName("§7Kit §e§lViper");
/* 329 */         ArrayList indiob = new ArrayList();
/* 330 */         indiob.add((Main.kits.getString("ViperLore").replace("&", "§")));
/* 331 */         metapyro.setLore(indiob);
/* 332 */         pyro.setItemMeta(metapyro);
/* 333 */         kits.addItem(new ItemStack[] { pyro });
/*     */       }
/* 335 */       if (p.hasPermission("kitpvp.kit.stomper")) {
/* 336 */         ItemStack pyro = new ItemStack(Material.IRON_BOOTS);
/* 337 */         ItemMeta metapyro = pyro.getItemMeta();
/* 338 */         metapyro.setDisplayName("§7Kit §e§lStomper");
/* 339 */         ArrayList indiob = new ArrayList();
/* 341 */         indiob.add((Main.kits.getString("StomperLore").replace("&", "§")));
/* 342 */         metapyro.setLore(indiob);
/* 343 */         pyro.setItemMeta(metapyro);
/* 344 */         kits.addItem(new ItemStack[] { pyro });
/*     */       }
/* 346 */       if (p.hasPermission("kitpvp.kit.deshfire")) {
/* 347 */         ItemStack pyro = new ItemStack(Material.REDSTONE_BLOCK);
/* 348 */         ItemMeta metapyro = pyro.getItemMeta();
/* 349 */         metapyro.setDisplayName("§7Kit §e§lDeshfire");
/* 350 */         ArrayList indiob = new ArrayList();
/* 351 */         indiob.add((Main.kits.getString("DeshfireLore").replace("&", "§")));
/* 352 */         
/* 353 */         metapyro.setLore(indiob);
/* 354 */         pyro.setItemMeta(metapyro);
/* 355 */         kits.addItem(new ItemStack[] { pyro });
/*     */       }
/* 368 */       if (p.hasPermission("kitpvp.kit.critical")) {
/* 369 */         ItemStack pyro = new ItemStack(Material.GOLDEN_APPLE);
/* 370 */         ItemMeta metapyro = pyro.getItemMeta();
/* 371 */         metapyro.setDisplayName("§7Kit §e§lCritical");
/* 372 */         ArrayList indiob = new ArrayList();
/* 373 */         indiob.add((Main.kits.getString("CriticalLore").replace("&", "§")));
/* 374 */         metapyro.setLore(indiob);
/* 375 */         pyro.setItemMeta(metapyro);
/* 376 */         kits.addItem(new ItemStack[] { pyro });
/*     */       }
/* 378 */       if (p.hasPermission("kitpvp.kit.gladiator")) {
/* 379 */         ItemStack pyro = new ItemStack(Material.IRON_BARS);
/* 380 */         ItemMeta metapyro = pyro.getItemMeta();
/* 381 */         metapyro.setDisplayName("§7Kit §e§lGladiator");
/* 382 */         ArrayList indiob = new ArrayList();
/* 383 */         indiob.add((Main.kits.getString("GladiatorLore").replace("&", "§")));
/* 384 */         metapyro.setLore(indiob);
/* 385 */         pyro.setItemMeta(metapyro);
/* 386 */         kits.addItem(new ItemStack[] { pyro });
/*     */       }
/* 388 */       if (p.hasPermission("kitpvp.kit.thor")) {
/* 389 */         ItemStack pyro = new ItemStack(Material.GOLDEN_AXE);
/* 390 */         ItemMeta metapyro = pyro.getItemMeta();
/* 391 */         metapyro.setDisplayName("§7Kit §e§lThor");
/* 392 */         ArrayList indiob = new ArrayList();
/* 393 */         indiob.add(Main.kits.getString("ThorLore").replace("&", "§"));
/* 395 */         metapyro.setLore(indiob);
/* 396 */         pyro.setItemMeta(metapyro);
/* 397 */         kits.addItem(new ItemStack[] { pyro });
/*     */       }
/* 399 */       if (p.hasPermission("kitpvp.kit.doublejump")) {
/* 400 */         ItemStack pyro = new ItemStack(Material.CHAINMAIL_BOOTS);
/* 401 */         ItemMeta metapyro = pyro.getItemMeta();
/* 402 */         metapyro.setDisplayName("§7Kit §e§lDoubleJump");
/* 403 */         ArrayList indiob = new ArrayList();
/* 404 */      indiob.add(Main.kits.getString("DoubleJumpLore").replace("&", "§"));
/* 406 */         metapyro.setLore(indiob);
/* 407 */         pyro.setItemMeta(metapyro);
/* 408 */         kits.addItem(new ItemStack[] { pyro });
/*     */       }
/* 410 */       if (p.hasPermission("kitpvp.kit.bomber")) {
/* 411 */         ItemStack pyro = new ItemStack(Material.TNT);
/* 412 */         ItemMeta metapyro = pyro.getItemMeta();
/* 413 */         metapyro.setDisplayName("§7Kit §e§lBomber");
/* 414 */         ArrayList indiob = new ArrayList();
/* 415 */         indiob.add(Main.kits.getString("BomberLore").replace("&", "§"));
/* 417 */         metapyro.setLore(indiob);
/* 418 */         pyro.setItemMeta(metapyro);
/* 419 */         kits.addItem(new ItemStack[] { pyro });
/*     */       }
/* 421 */       if (p.hasPermission("kitpvp.kit.wasp")) {
/* 422 */         ItemStack pyro = new ItemStack(Material.BLAZE_ROD);
/* 423 */         ItemMeta metapyro = pyro.getItemMeta();
/* 424 */         metapyro.setDisplayName("§7Kit §e§lWasp");
/* 425 */         ArrayList indiob = new ArrayList();
/* 426 */        indiob.add(Main.kits.getString("WaspLore").replace("&", "§"));
/* 428 */         metapyro.setLore(indiob);
/* 429 */         pyro.setItemMeta(metapyro);
/* 430 */         kits.addItem(new ItemStack[] { pyro });
/*     */       }
/*     */       
/* 421 */       if (p.hasPermission("kitpvp.kit.confuser")) {
/* 422 */         ItemStack pyro = new ItemStack(Material.POTION);
/* 423 */         ItemMeta metapyro = pyro.getItemMeta();
/* 424 */         metapyro.setDisplayName("§7Kit §e§lConfuser");
/* 425 */         ArrayList indiob = new ArrayList();
/* 426 */        indiob.add(Main.kits.getString("ConfuserLore").replace("&", "§"));
/* 428 */         metapyro.setLore(indiob);
/* 429 */         pyro.setItemMeta(metapyro);
/* 430 */         kits.addItem(new ItemStack[] { pyro });
/*     */       }
/* 432 */       if (p.hasPermission("kitpvp.kit.airman")) {
/* 433 */         ItemStack pyro = new ItemStack(Material.FEATHER);
/* 434 */         ItemMeta metapyro = pyro.getItemMeta();
/* 435 */         metapyro.setDisplayName("§7Kit §e§lAirman");
/* 436 */         ArrayList indiob = new ArrayList();
/* 437 */         indiob.add(Main.kits.getString("AirmanLore").replace("&", "§"));
/* 439 */         metapyro.setLore(indiob);
/* 440 */         pyro.setItemMeta(metapyro);
/* 441 */         kits.addItem(new ItemStack[] { pyro });
/*     */       }
/* 443 */       if (p.hasPermission("kitpvp.kit.switcher"))
/*     */       {
/* 445 */         ItemStack pyro = new ItemStack(Material.SNOWBALL, 16);
/* 446 */         ItemMeta metapyro = pyro.getItemMeta();
/* 447 */         metapyro.setDisplayName("§7Kit §e§lSwitcher");
/* 448 */         ArrayList indiob = new ArrayList();
/* 449 */         indiob.add(Main.kits.getString("SwitcherLore").replace("&", "§"));
/* 451 */         metapyro.setLore(indiob);
/* 452 */         pyro.setItemMeta(metapyro);
/* 453 */         kits.addItem(new ItemStack[] { pyro });
/*     */       }
/* 455 */       if (p.hasPermission("kitpvp.kit.ninja")) {
/* 456 */         ItemStack pyro = new ItemStack(Material.COAL);
/* 457 */         ItemMeta metapyro = pyro.getItemMeta();
/* 458 */         metapyro.setDisplayName("§7Kit §e§lNinja");
/* 459 */         ArrayList indiob = new ArrayList();
/* 460 */         metapyro.setLore(indiob);
/* 461 */         indiob.add(Main.kits.getString("NinjaLore").replace("&", "§"));
/* 464 */         metapyro.setLore(indiob);
/* 465 */         pyro.setItemMeta(metapyro);
/* 466 */         kits.addItem(new ItemStack[] { pyro });
/*     */       }
if (p.hasPermission("kitpvp.kit.creeper")) {
/* 283 */         ItemStack pyro = new ItemStack(Material.COMPARATOR);
/* 284 */         ItemMeta metapyro = pyro.getItemMeta();
/* 285 */         metapyro.setDisplayName("§7Kit §e§lCreeper");
/* 286 */         ArrayList indiob = new ArrayList();
/* 287 */         indiob.add(API.cor(Main.kits.getString("CreeperLore")));
/* 289 */         metapyro.setLore(indiob);
/* 290 */         pyro.setItemMeta(metapyro);
	/* 280 */     kits.addItem(new ItemStack[] { pyro });
	/*     */       /*     */       } 
/* 468 */       if (p.hasPermission("kitpvp.kit.cactus")) {
/* 469 */         ItemStack pyro = new ItemStack(Material.CACTUS);
/* 470 */         ItemMeta metapyro = pyro.getItemMeta();
/* 471 */         metapyro.setDisplayName("§7Kit §e§lCactus");
/* 472 */         ArrayList indiob = new ArrayList();
/* 473 */         indiob.add(Main.kits.getString("CactusLore").replace("&", "§"));
/*     */         
/* 475 */         metapyro.setLore(indiob);
/* 476 */         pyro.setItemMeta(metapyro);
/* 477 */         kits.addItem(new ItemStack[] { pyro });
/*     */       }
if (p.hasPermission("kitpvp.kit.antistomper")) {
/* 469 */         ItemStack pyro = new ItemStack(Material.NETHERITE_HELMET);
/* 470 */         ItemMeta metapyro = pyro.getItemMeta();
/* 471 */         metapyro.setDisplayName("§7Kit §e§lAntiStomper");
/* 472 */         ArrayList indiob = new ArrayList();
/* 473 */         indiob.add(Main.kits.getString("AntiStomperLore").replace("&", "§"));
/*     */         
/* 475 */         metapyro.setLore(indiob);
/* 476 */         pyro.setItemMeta(metapyro);
/* 477 */         kits.addItem(new ItemStack[] { pyro });
/*     */       }
if (p.hasPermission("kitpvp.kit.berserker")) {
/* 555 */         ItemStack pyro = new ItemStack(Material.GOLDEN_CARROT);
/* 556 */         ItemMeta metapyro = pyro.getItemMeta();
/* 557 */         metapyro.setDisplayName("§7Kit §e§lBerserker");
/* 558 */         ArrayList indiob = new ArrayList();
/* 559 */         indiob.add((Main.kits.getString("BerserkerLore")).replace("&", "§"));
/* 561 */         metapyro.setLore(indiob);
/* 562 */         pyro.setItemMeta(metapyro);
	/* 280 */     kits.addItem(new ItemStack[] { pyro });      
/*     */        } 
/* 479 */       if (p.hasPermission("kitpvp.kit.pyro")) {
/* 480 */         ItemStack pyro = new ItemStack(Material.FLINT_AND_STEEL);
/* 481 */         ItemMeta metapyro = pyro.getItemMeta();
/* 482 */         metapyro.setDisplayName("§7Kit §e§lPyro");
/* 483 */         ArrayList indiob = new ArrayList();
/* 484 */         indiob.add(Main.kits.getString("PyroLore").replace("&", "§"));
/*     */         
/* 486 */         metapyro.setLore(indiob);
/* 487 */         pyro.setItemMeta(metapyro);
/* 488 */         kits.addItem(new ItemStack[] { pyro });
/*     */       }
/* 490 */       if (p.hasPermission("kitpvp.kit.jumper")) {
/* 491 */         ItemStack pyro = new ItemStack(Material.LEATHER_BOOTS);
/* 492 */         ItemMeta metapyro = pyro.getItemMeta();
/* 493 */         metapyro.setDisplayName("§7Kit §e§lJumper");
/* 494 */         ArrayList indiob = new ArrayList();
/* 495 */         indiob.add(Main.kits.getString("JumperLore").replace("&", "§"));
/*     */         
/* 497 */         metapyro.setLore(indiob);
/* 498 */         pyro.setItemMeta(metapyro);
/* 499 */         kits.addItem(new ItemStack[] { pyro });
/*     */       }
/*     */       
/* 502 */       if (p.hasPermission("kitpvp.kit.timelord"))
/*     */       {
/* 504 */         ItemStack pyro = new ItemStack(Material.CLOCK);
/* 505 */         ItemMeta metapyro = pyro.getItemMeta();
/* 506 */         metapyro.setDisplayName("§7Kit §e§lTimeLord");
/* 507 */         ArrayList indiob = new ArrayList();
/* 508 */         indiob.add(Main.kits.getString("TimelordLore").replace("&", "§"));
/* 511 */         metapyro.setLore(indiob);
/* 512 */         pyro.setItemMeta(metapyro);
/* 513 */         kits.addItem(new ItemStack[] { pyro });
/*     */       }
/* 515 */       if (p.hasPermission("kitpvp.kit.fisherman"))
/*     */       {
/* 517 */         ItemStack pyro = new ItemStack(Material.FISHING_ROD);
/* 518 */         ItemMeta metapyro = pyro.getItemMeta();
/* 519 */         metapyro.setDisplayName("§7Kit §e§lFisherman");
/* 520 */         ArrayList indiob = new ArrayList();
/* 521 */         indiob.add(Main.kits.getString("FishermanLore").replace("&", "§"));
/* 523 */         metapyro.setLore(indiob);
/* 524 */         pyro.setItemMeta(metapyro);
/* 525 */         kits.addItem(new ItemStack[] { pyro });
/*     */       }
/* 527 */       if (p.hasPermission("kitpvp.kit.spiderman"))
/*     */       {
/* 529 */         ItemStack pyro = new ItemStack(Material.COBWEB);
/* 530 */         ItemMeta metapyro = pyro.getItemMeta();
/* 531 */         metapyro.setDisplayName("§7Kit §e§lSpiderman");
/* 532 */         ArrayList indiob = new ArrayList();
/* 533 */         indiob.add(Main.kits.getString("SpidermanLore").replace("&", "§"));
/* 536 */         metapyro.setLore(indiob);
/* 537 */         pyro.setItemMeta(metapyro);
/* 538 */         kits.addItem(new ItemStack[] { pyro });
/*     */       }
/* 540 */       if (p.hasPermission("kitpvp.kit.warper"))
/*     */       {
/* 542 */         ItemStack pyro = new ItemStack(Material.ENDER_PEARL);
/* 543 */         ItemMeta metapyro = pyro.getItemMeta();
/* 544 */         metapyro.setDisplayName("§7Kit §e§lWarper");
/* 545 */         ArrayList indiob = new ArrayList();
/* 546 */         indiob.add(Main.kits.getString("WarperLore").replace("&", "§"));
/*     */         
/* 549 */         metapyro.setLore(indiob);
/* 550 */         pyro.setItemMeta(metapyro);
/* 551 */         kits.addItem(new ItemStack[] { pyro });
/*     */       }
/* 540 */       if (p.hasPermission("kitpvp.kit.vampire"))
/*     */       {
/* 542 */         ItemStack pyro = new ItemStack(Material.GOLD_NUGGET);
/* 543 */         ItemMeta metapyro = pyro.getItemMeta();
/* 544 */         metapyro.setDisplayName("§7Kit §e§lVampire");
/* 545 */         ArrayList indiob = new ArrayList();
/* 546 */         indiob.add(Main.kits.getString("VampireLore").replace("&", "§"));
/*     */         
/* 549 */         metapyro.setLore(indiob);
/* 550 */         pyro.setItemMeta(metapyro);
/* 551 */         kits.addItem(new ItemStack[] { pyro });
/*     */       }
/* 553 */       if (p.hasPermission("kitpvp.kit.naruto"))
/*     */       {
/* 555 */         ItemStack pyro = new ItemStack(Material.NETHER_STAR);
/* 556 */         ItemMeta metapyro = pyro.getItemMeta();
/* 557 */         metapyro.setDisplayName("§7Kit §e§lNaruto");
/* 558 */         ArrayList indiob = new ArrayList();
/* 559 */         indiob.add(Main.kits.getString("NarutoLore").replace("&", "§"));
/* 561 */         metapyro.setLore(indiob);
/* 562 */         pyro.setItemMeta(metapyro);
/* 563 */         kits.addItem(new ItemStack[] { pyro });
/*     */       }
/* 553 */       if (p.hasPermission("kitpvp.kit.sonic"))
/*     */       {
/* 555 */         ItemStack pyro = new ItemStack(Material.LAPIS_BLOCK);
/* 556 */         ItemMeta metapyro = pyro.getItemMeta();
/* 557 */         metapyro.setDisplayName("§7Kit §e§lSonic");
/* 558 */         ArrayList indiob = new ArrayList();
/* 559 */         indiob.add((Main.kits.getString("SonicLore")).replace("&", "§"));
/* 560 */         
/* 561 */         metapyro.setLore(indiob);
/* 562 */         pyro.setItemMeta(metapyro);
/* 563 */         kits.addItem(new ItemStack[] { pyro });
/*     */       }
/* 553 */       if (p.hasPermission("kitpvp.kit.phantom"))
/*     */       {
/* 555 */         ItemStack pyro = new ItemStack(Material.BOOK);
/* 556 */         ItemMeta metapyro = pyro.getItemMeta();
/* 557 */         metapyro.setDisplayName("§7Kit §e§lPhantom");
/* 558 */         ArrayList indiob = new ArrayList();
/* 559 */         indiob.add((Main.kits.getString("PhantomLore")).replace("&", "§"));
/* 560 */         
/* 561 */         metapyro.setLore(indiob);
/* 562 */         pyro.setItemMeta(metapyro);
/* 563 */         kits.addItem(new ItemStack[] { pyro });
/*     */       }
/* 553 */       if (p.hasPermission("kitpvp.kit.camel"))
/*     */       {
/* 555 */         ItemStack pyro = new ItemStack(Material.SAND);
/* 556 */         ItemMeta metapyro = pyro.getItemMeta();
/* 557 */         metapyro.setDisplayName("§7Kit §e§lCamel");
/* 558 */         ArrayList indiob = new ArrayList();
/* 559 */         indiob.add((Main.kits.getString("CamelLore")).replace("&", "§"));
/* 560 */         
/* 561 */         metapyro.setLore(indiob);
/* 562 */         pyro.setItemMeta(metapyro);
/* 563 */         kits.addItem(new ItemStack[] { pyro });
/*     */       }
/* 553 */       if (p.hasPermission("kitpvp.kit.viking"))
/*     */       {
/* 555 */         ItemStack pyro = new ItemStack(Material.IRON_AXE);
/* 556 */         ItemMeta metapyro = pyro.getItemMeta();
/* 557 */         metapyro.setDisplayName("§7Kit §e§lViking");
/* 558 */         ArrayList indiob = new ArrayList();
/* 559 */         indiob.add((Main.kits.getString("VikingLore")).replace("&", "§"));
/* 560 */         
/* 561 */         metapyro.setLore(indiob);
/* 562 */         pyro.setItemMeta(metapyro);
/* 563 */         kits.addItem(new ItemStack[] { pyro });
/*     */       }
/* 553 */       if (p.hasPermission("kitpvp.kit.poseidon"))
/*     */       {
/* 555 */         ItemStack pyro = new ItemStack(Material.WATER_BUCKET);
/* 556 */         ItemMeta metapyro = pyro.getItemMeta();
/* 557 */         metapyro.setDisplayName("§7Kit §e§lPoseidon");
/* 558 */         ArrayList indiob = new ArrayList();
/* 559 */         indiob.add((Main.kits.getString("PoseidonLore")).replace("&", "§"));
/* 560 */         
/* 561 */         metapyro.setLore(indiob);
/* 562 */         pyro.setItemMeta(metapyro);
/* 563 */         kits.addItem(new ItemStack[] { pyro });
/*     */       }
if (p.hasPermission("kitpvp.kit.milkman"))
/*     */       {
/* 555 */         ItemStack pyro = new ItemStack(Material.MILK_BUCKET);
/* 556 */         ItemMeta metapyro = pyro.getItemMeta();
/* 557 */         metapyro.setDisplayName("§7Kit §e§lMilkMan");
/* 558 */         ArrayList indiob = new ArrayList();
/* 559 */         indiob.add((Main.kits.getString("MilkmanLore")).replace("&", "§"));
/* 560 */         
/* 561 */         metapyro.setLore(indiob);
/* 562 */         pyro.setItemMeta(metapyro);
/* 563 */         kits.addItem(new ItemStack[] { pyro });
/*     */       }
/* 553 */       if (p.hasPermission("kitpvp.kit.anchor"))
/*     */       {
/* 555 */         ItemStack pyro = new ItemStack(Material.ANVIL);
/* 556 */         ItemMeta metapyro = pyro.getItemMeta();
/* 557 */         metapyro.setDisplayName("§7Kit §e§lAnchor");
/* 558 */         ArrayList indiob = new ArrayList();
/* 559 */         indiob.add((Main.kits.getString("AnchorLore")).replace("&", "§"));
/* 560 */         
/* 561 */         metapyro.setLore(indiob);
/* 562 */         pyro.setItemMeta(metapyro);
/* 563 */         kits.addItem(new ItemStack[] { pyro });
/*     */       }
/* 553 */       if (p.hasPermission("kitpvp.kit.resouper"))
/*     */       {
/* 555 */         ItemStack pyro = new ItemStack(Material.MUSHROOM_STEW);
/* 556 */         ItemMeta metapyro = pyro.getItemMeta();
/* 557 */         metapyro.setDisplayName("§7Kit §e§lResouper");
/* 558 */         ArrayList indiob = new ArrayList();
/* 559 */         indiob.add((Main.kits.getString("ResouperLore")).replace("&", "§"));
/* 560 */         
/* 561 */         metapyro.setLore(indiob);
/* 562 */         pyro.setItemMeta(metapyro);
/* 563 */         kits.addItem(new ItemStack[] { pyro });
/*     */       }
      if (p.hasPermission("kitpvp.kit.monk"))
/*     */       {
/* 555 */         ItemStack pyro = new ItemStack(Material.BLAZE_ROD);
/* 556 */         ItemMeta metapyro = pyro.getItemMeta();
/* 557 */         metapyro.setDisplayName("§7Kit §e§lMonk");
/* 558 */         ArrayList indiob = new ArrayList();
/* 559 */         indiob.add((Main.kits.getString("MonkLore")).replace("&", "§"));
/* 560 */         
/* 561 */         metapyro.setLore(indiob);
/* 562 */         pyro.setItemMeta(metapyro);
/* 563 */         kits.addItem(new ItemStack[] { pyro });
/*     */       }
/* 553 */       if (p.hasPermission("kitpvp.kit.fireman"))
/*     */       {
/* 555 */         ItemStack pyro = new ItemStack(Material.LAVA_BUCKET);
/* 556 */         ItemMeta metapyro = pyro.getItemMeta();
/* 557 */         metapyro.setDisplayName("§7Kit §e§lFireman");
/* 558 */         ArrayList indiob = new ArrayList();
/* 559 */         indiob.add((Main.kits.getString("FiremanLore")).replace("&", "§"));
/* 560 */         
/* 561 */         metapyro.setLore(indiob);
/* 562 */         pyro.setItemMeta(metapyro);
/* 563 */         kits.addItem(new ItemStack[] { pyro });
/*     */       }
/* 553 */       if (p.hasPermission("kitpvp.kit.ryu"))
/*     */       {
/* 555 */         ItemStack pyro = new ItemStack(Material.BEACON);
/* 556 */         ItemMeta metapyro = pyro.getItemMeta();
/* 557 */         metapyro.setDisplayName("§7Kit §e§lRyu");
/* 558 */         ArrayList indiob = new ArrayList();
/* 559 */         indiob.add((Main.kits.getString("RyuLore")).replace("&", "§"));
/* 560 */         
/* 561 */         metapyro.setLore(indiob);
/* 562 */         pyro.setItemMeta(metapyro);
/* 563 */         kits.addItem(new ItemStack[] { pyro });
/*     */       }
if (p.hasPermission("kitpvp.kit.shielder"))
/*     */       {
/* 555 */         ItemStack pyro = new ItemStack(Material.SHIELD);
/* 556 */         ItemMeta metapyro = pyro.getItemMeta();
/* 557 */         metapyro.setDisplayName("§7Kit §e§lShielder");
/* 558 */         ArrayList indiob = new ArrayList();
/* 559 */         indiob.add((Main.kits.getString("ShielderLore")).replace("&", "§"));
/* 560 */         
/* 561 */         metapyro.setLore(indiob);
/* 562 */         pyro.setItemMeta(metapyro);
/* 563 */         kits.addItem(new ItemStack[] { pyro });
/*     */       }

/*     */      
/*     */       
/* 575 */       p.openInventory(kits);
/*     */     }
/* 577 */     return true;
/*     */   }
/*     */ }


/* Location:              D:\Desktop\video\Minhas Coisas do Desktop\KP-PVPvB12 (1).jar!\me\RafaelAulerDeMeloAraujo\SpecialAbility\NewKitMenu.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       0.7.1
 */
