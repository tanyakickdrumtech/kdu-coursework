import "./Header.css";

/**
 * Interface representing the props for the Header component.
 */
interface HeaderProps {
	/** The name of the person. */
	name: string;
	/** The full name of the person. */
	fullName: string;
	/** The qualification of the person. */
	qualification: string;
}

/**
 * Functional component representing the header details.
 *
 * @param {HeaderProps} props - The props for the Header component.
 * @returns {JSX.Element} - The rendered header details.
 */
export function Header({
	name,
	fullName,
	qualification,
}: HeaderProps): JSX.Element {
	return (
		<div className="header-details">
			<h2>
				<i>{name}</i>
			</h2>
			<h3>
				<i>{fullName} </i>
			</h3>
			<h2>
				<i>{qualification} </i>
			</h2>
		</div>
	);
}
