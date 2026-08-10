import Link from 'next/link';

export default function NavbarTop() {
  return (
    <nav className="border-b border-slate-200 bg-white/80 backdrop-blur">
      <div className="mx-auto flex max-w-6xl items-center justify-between px-6 py-4">
        <Link href="/" className="text-xl font-semibold text-slate-900">
          Game Shop
        </Link>

        <div className="flex items-center gap-4 text-sm text-slate-700">
          <Link href="/" className="transition hover:text-slate-950">
            Home
          </Link>
          <Link href="/shop" className="transition hover:text-slate-950">
            Shop Products
          </Link>
          <Link href="/cart" className="transition hover:text-slate-950">
            My Cart
          </Link>

          {/* Todo: Hacer que cuando se logue, aparezca el nombre del cliente y se quiten los botones */}
          <Link href="/login" className="transition hover:text-slate-950">
            Login
          </Link>
          <Link href="/register" className="transition hover:text-slate-950">
            Register
          </Link>
        </div>
      </div>
    </nav>
  );
}