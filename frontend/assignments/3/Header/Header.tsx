/**
 * Header component displays the application header containing navigation links and a menu for mobile view.
 */
import React, { useState, useEffect } from "react";
import {
	AppBar,
	Toolbar,
	Typography,
	IconButton,
	Box,
	Menu,
	MenuItem,
	ListItemText,
} from "@mui/material";
import { Link } from "react-router-dom";
import MenuIcon from "@mui/icons-material/Menu";
import stockMarket from "../assets/stockMarket.png";
import { headerStyle } from "../styles/HeaderStyle";

const Header: React.FC = () => {
	const [isMobile, setIsMobile] = useState(false);
	const [anchorEl, setAnchorEl] = useState<null | HTMLElement>(null);
	const [activeLink, setActiveLink] = useState("");

	/**
	 * Handles click event for navigation links.
	 * @param {string} link - The link to navigate to.
	 */
	const handleLinkClick = (link: string): void => {
		setActiveLink(link);
	};

	/**
	 * Handles opening the menu on mobile view.
	 * @param {React.MouseEvent<HTMLElement>} event - The event object.
	 */
	const handleMenuOpen = (event: React.MouseEvent<HTMLElement>): void => {
		setAnchorEl(event.currentTarget);
	};

	/**
	 * Handles closing the menu.
	 */
	const handleMenuClose = (): void => {
		setAnchorEl(null);
	};

	useEffect(() => {
		const handleResize = (): void => {
			setIsMobile(window.innerWidth < 768);
		};

		window.addEventListener("resize", handleResize);
		handleResize();

		return () => window.removeEventListener("resize", handleResize);
	}, []);

	return (
		<div style={headerStyle}>
			<AppBar position="static">
				<Toolbar>
					<IconButton edge="start" color="inherit" aria-label="menu">
						<Link to="/explore">
							<img
								style={{ width: "42px", height: "32px" }}
								src={stockMarket}
								alt="Stock"
								className="logo-image"
							/>
						</Link>
					</IconButton>
					<Typography variant="h6" style={{ flexGrow: 1 }}>
						<Link
							to="/dashboard"
							style={{
								color: "inherit",
								textDecoration: "none",
								fontSize: "30px",
							}}
						>
							KDU Stock Market
						</Link>
					</Typography>
					{isMobile && (
						<IconButton
							color="inherit"
							aria-label="open menu"
							edge="end"
							onClick={handleMenuOpen}
						>
							<MenuIcon />
						</IconButton>
					)}

					{!isMobile && (
						<Box>
							<Typography variant="h6">
								<Link
									to="/summarizer"
									style={{
										color: "inherit",
										textDecoration: "none",
										marginRight: "20px",
									}}
									onClick={handleLinkClick.bind(null, "summarizer")}
								>
									Summarizer
								</Link>

								<Link
									to="/portfolio"
									style={{
										color: "inherit",
										textDecoration: "none",
										marginRight: "20px",
									}}
									onClick={handleLinkClick.bind(null, "portfolio")}
								>
									My Portfolio
								</Link>
							</Typography>
						</Box>
					)}
				</Toolbar>
			</AppBar>

			<Menu
				id="menu-appbar"
				anchorEl={anchorEl}
				anchorOrigin={{
					vertical: "top",
					horizontal: "right",
				}}
				transformOrigin={{
					vertical: "top",
					horizontal: "right",
				}}
				open={Boolean(anchorEl)}
				onClose={handleMenuClose}
			>
				<MenuItem onClick={handleLinkClick.bind(null, "summarizer")}>
					<ListItemText>Summarizer</ListItemText>
				</MenuItem>
				<MenuItem onClick={handleLinkClick.bind(null, "portfolio")}>
					<ListItemText>Portfolio</ListItemText>
				</MenuItem>
			</Menu>
		</div>
	);
};

export default Header;
